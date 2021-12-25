package com.danceEngine.tiled;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class TileMap {
	public BufferedImage mapImage;
	public ArrayList<Layer> layers;
	public Tileset tileset;
	public HashMap<String, ArrayList<GameObject>> gameObjects;
	public ArrayList<GameObject> gameObjectList;
	public HashMap<Integer, GameObject> gameObjectsById = new HashMap<>();
	
	public TileMap(BufferedImage mapImage, ArrayList<Layer> layers, Tileset tileset, HashMap<String, ArrayList<GameObject>> gameObjects) {
		super();
		this.mapImage = mapImage;
		this.layers = layers;
		this.tileset = tileset;
		this.gameObjects = gameObjects;
		this.gameObjectList = new ArrayList<>();
		for(ArrayList<GameObject> list : gameObjects.values()) {
			this.gameObjectList.addAll(list);
			for(GameObject go : list) {
				gameObjectsById.put(go.id, go);
			}
		}
	}
	
	public ArrayList<GameObject> getGameObjectsByType(String type) {
		return gameObjects.get(type);
	}
	
	public GameObject getFirstGameObjByType(String type) {
		return getGameObjectsByType(type).get(0);
	}
	
	public ArrayList<GameObject> getGameObjects() {
		return gameObjectList;
	}
	
	public GameObject getGameObjectByID(int id) {
		return gameObjectsById.get(id);
	}
	
	public BufferedImage getImgFromGID(int gid) {
		return tileset.tiles[gid].image;
	}
	
	public Tile getTile(int gid) {
		return tileset.tiles[gid];
	}
	
	
}
