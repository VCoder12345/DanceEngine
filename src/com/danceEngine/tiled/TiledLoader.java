package com.danceEngine.tiled;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.danceEngine.utils.Vector2;

import parser.XMLNode;
import parser.XMLParser;



public class TiledLoader {
	
	public static TileMap loadMap(String inPath) throws IOException {
		XMLNode root = XMLParser.parseFile(inPath);
		ArrayList<Layer> layers = new ArrayList<>();
		Tileset tileset = null;
		HashMap<String, ArrayList<GameObject>> gameObjs = new HashMap<>();
		
		for(XMLNode child : root.getChilds()) {
			switch(child.name) {
			case "tileset":
				tileset = loadTileset(child, inPath);
				break;
			case "layer":
				Layer layer = loadLayer(child);
				layers.add(layer);
				break;
			case "objectgroup":
				gameObjs.putAll(loadGameObjects(child));
				break;
			}
		}
		
		BufferedImage mapImg = imageFromLayers(layers, tileset);
		return new TileMap(mapImg, layers, tileset, gameObjs);	
	}
	
	private static HashMap<String, ArrayList<GameObject>> loadGameObjects(XMLNode node) {
		HashMap<String, ArrayList<GameObject>> gameObjs = new HashMap<>();
		
		for(XMLNode objNode : node.getChilds()) {
			String name = objNode.getAttribute("name");
			String type = objNode.getAttribute("type");
			int id = Integer.parseInt(objNode.getAttribute("id"));
			float x = Float.parseFloat(objNode.getAttribute("x"));
			float y = Float.parseFloat(objNode.getAttribute("y"));
			float width = 0;
			float height = 0;
			if(objNode.hasAttribute("width")) {
				width = Float.parseFloat(objNode.getAttribute("width"));
				height = Float.parseFloat(objNode.getAttribute("height"));
				y -= height;
			}
			int tileIndex = 0;
			if(objNode.hasAttribute("gid")) {
				int gid = Integer.parseInt(objNode.getAttribute("gid"));
				tileIndex = gid;
			}
			
			GameObject gameObj = new GameObject(name, type, new Vector2(x, y), new Vector2(width, height), id, tileIndex);
			
			if(objNode.hasChildWithName("properties")) {
				for(XMLNode propNode : objNode.firstChildWithName("properties").getChilds()) {
					String pname = propNode.getAttribute("name");
					String pvalue = propNode.getAttribute("value");
					gameObj.addProperty(pname, pvalue);
				}
			}
			
			if(!gameObjs.containsKey(type)) {
				gameObjs.put(type, new ArrayList<>());
			}
			gameObjs.get(type).add(gameObj);
		}
		
		return gameObjs;
	}

	private static Tileset loadTileset(XMLNode node, String inPath) throws IOException {
		String source = node.getAttribute("source");
		int p = source.lastIndexOf('/');
		source = source.substring(p + 1);
		String dir = inPath.substring(0, inPath.lastIndexOf('/') + 1);
		XMLNode tNode = XMLParser.parseFile(dir + source);
		int tilewidth = tNode.getIntAttribute("tilewidth");
		int tileheight = tNode.getIntAttribute("tileheight");
		String imgSource = tNode.firstChildWithName("image").getAttribute("source");
		imgSource = "res/maps/" + imgSource.substring(imgSource.lastIndexOf('/') + 1);
		System.out.println(imgSource);
		BufferedImage img = ImageIO.read(new File(imgSource));
		int spacing = 0;
		if(tNode.hasAttribute("spacing")) {
			spacing = tNode.getIntAttribute("spacing");
		}
		int width = tNode.getIntAttribute("columns");
		int height = img.getHeight() / (tileheight);
		
		int tilecount = tNode.getIntAttribute("tilecount");
		
		Tile[] tiles = new Tile[tilecount + 1];
		for(int i = 0; i <= tilecount; ++i) {
			tiles[i] = new Tile();
			tiles[i].id = i;
		}
		
		//images
		tiles[0].image = new BufferedImage(tilewidth, tileheight, BufferedImage.TYPE_INT_ARGB);
		for(int i = 0; i < tilecount; ++i) {
			int x = i % width;
			int y = i / width;
			BufferedImage timg = img.getSubimage(x * (tilewidth + spacing), y * (tileheight + spacing), tilewidth, tileheight);
			tiles[i + 1].image = timg;
		}
		
		//properties
		if(tNode.hasChildWithName("tile")) {
			for(XMLNode tileNode : tNode.childsWithName("tile")) {
				int id = Integer.parseInt(tileNode.getAttribute("id")) + 1;
				for(XMLNode childNode : tileNode.getChilds()) {
					if(childNode.name.equals("properties")) {
						for(XMLNode pNode : childNode.childsWithName("property")) {
							String name = pNode.getAttribute("name");
							String value = pNode.getAttribute("value");
							
							tiles[id].addProperty(name, value);
						}
					}
				}
					
			}
		}
		
		
		return new Tileset(tilewidth, tileheight, tiles);
	}

	private static Layer loadLayer(XMLNode node) {
		int width = Integer.parseInt(node.getAttribute("width"));
		int height = Integer.parseInt(node.getAttribute("height"));
		String data = node.firstChildWithName("data").text;
		String[] sIndices = data.split(",");
		int[][] indices = new int[width][height];
		for(int i = 0; i < sIndices.length; ++i) {
			int x = i % width;
			int y = i / width;
			
			indices[x][y] = Integer.parseInt(sIndices[i]);
		}
		
		return new Layer(width, height, indices);
	}

	public static BufferedImage imageFromLayers(ArrayList<Layer> layers, Tileset tileset) {
		int imgW = layers.get(0).width * tileset.tilewidth;
		int imgH = layers.get(0).height * tileset.tileheight;
		BufferedImage finalImg = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = finalImg.createGraphics();
		for(Layer layer : layers) {
			for(int y = 0; y < layer.height; ++y) {
				for(int x = 0; x < layer.width; ++x) {
					int index = layer.tileIndices[x][y];
					BufferedImage img = tileset.tiles[index].image;
					g2d.drawImage(img, x * tileset.tilewidth, y * tileset.tileheight, tileset.tilewidth, tileset.tileheight, null);
				}
			}
		}
		
		g2d.dispose();
		
		return finalImg;
	}
	
}
