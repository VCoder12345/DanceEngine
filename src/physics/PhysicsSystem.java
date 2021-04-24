package physics;

import java.util.ArrayList;

import ecs.ESystem;
import ecs.EcsManager;
import ecs.Entity;
import ecs.Transform;
import event.EventSystem;
import game.Game;
import maths.Vector2;
import physics.collision.Collider;
import physics.collision.CollisionCell;
import physics.collision.CollisionEvent;
import physics.collision.CollisionGrid;
import physics.collision.TriggerEvent;
import physics.collision.shape.AABB;

public class PhysicsSystem extends ESystem {

	@Override
	public void physics(float dt) {
		ArrayList<Entity> entities = getEntitiesWithTypes(Body.class, Collider.class, Transform.class);

		// apply forces
		for (Entity e : entities) {
			Body b = e.getComponentByType(Body.class);
			Transform t = e.getComponentByType(Transform.class);

			Vector2 gravity = new Vector2(0, 0.3f);
			gravity.mulE(b.mass);
			b.applyForce(gravity);
		}

		// integration
		for (Entity e : entities) {
			Body b = e.getComponentByType(Body.class);
			Transform t = e.getComponentByType(Transform.class);

			b.velocity.addE(b.acceleration.mul(dt));
			b.acceleration.mulE(0);
		}

		// collision
		for (int i = 0; i < entities.size(); ++i) {
			Entity e = entities.get(i);
			Body b = e.getComponentByType(Body.class);
			Transform t = e.getComponentByType(Transform.class);
			Collider collider = e.getComponentByType(Collider.class);

			// collision with grid
			CollisionGrid collGrid = Game.getPhysicsInfo().collisionGrid;

			t.position.x += b.velocity.x * dt;
			doCollisionX(collGrid, b, t);

			t.position.y += b.velocity.y * dt;
			doCollisionY(collGrid, b, t);

			// collision with window ground
			// doWindowCollision(b, t);

			// collisions with colliders
			for (int j = i + 1; j < entities.size(); ++j) {
				Entity oEntity = entities.get(j);
				Transform oTransform = oEntity.getComponentByType(Transform.class);
				Collider oCollider = oEntity.getComponentByType(Collider.class);

				if (oCollider.shape instanceof AABB) {
					if (collider.shape instanceof AABB) {
						AABB b1 = (AABB) collider.shape;
						AABB b2 = (AABB) oCollider.shape;

						Vector2 min1 = t.position.add(b1.offset);
						Vector2 max1 = min1.add(b1.size);

						Vector2 min2 = oTransform.position.add(b2.offset);
						Vector2 max2 = min2.add(b2.size);

						if (max1.x > min2.x && min1.x < max2.x && max1.y > min2.y && min1.y < max2.y) {
							EventSystem.submit(new TriggerEvent());
							EventSystem.submit(new TriggerEvent());
						}
					}
				}
			}
		}

		entities = getEntitiesWithTypes(Collider.class, Transform.class);
		// collision2
		for (int i = 0; i < entities.size(); ++i) {
			Entity e = entities.get(i);
			Transform t = e.getComponentByType(Transform.class);
			Collider collider = e.getComponentByType(Collider.class);

			// collisions with colliders
			for (int j = i + 1; j < entities.size(); ++j) {
				Entity oEntity = entities.get(j);
				Transform oTransform = oEntity.getComponentByType(Transform.class);
				Collider oCollider = oEntity.getComponentByType(Collider.class);

				if (oCollider.shape instanceof AABB) {
					if (collider.shape instanceof AABB) {
						AABB b1 = (AABB) collider.shape;
						AABB b2 = (AABB) oCollider.shape;

						Vector2 min1 = t.position.add(b1.offset);
						Vector2 max1 = min1.add(b1.size);

						Vector2 min2 = oTransform.position.add(b2.offset);
						Vector2 max2 = min2.add(b2.size);

						if (max1.x > min2.x && min1.x < max2.x && max1.y > min2.y && min1.y < max2.y) {
							EventSystem.submit(new TriggerEvent());
							EventSystem.submit(new TriggerEvent());
							System.out.println("collision: " + e.name + ", " + oEntity.name);
						}
					}
				}
			}
		}

	}

	private void doCollisionY(CollisionGrid collGrid, Body b, Transform t) {
		if (b.velocity.y == 0)
			return;

		b.onGround = false;

		int iy;
		if (b.velocity.y < 0) {
			iy = collGrid.indexYFromPos(t.position.y);
		} else {
			iy = collGrid.indexYFromPos(t.position.y + t.size.y);
		}

		int minIx = collGrid.indexXFromPos(t.position.x);
		int maxIx = collGrid.indexXFromPos(t.position.x + t.size.x);
		for (int x = minIx; x <= maxIx; ++x) {
			CollisionCell cell = collGrid.cells[x][iy];
			if (cell.solid) {
				if (b.velocity.y > 0) {
					t.position.y = collGrid.cellSize.y * iy - t.size.y - 1;
					b.velocity.y = 0;
					b.onGround = true;
				} else {
					t.position.y = collGrid.cellSize.y * (iy + 1);
					b.velocity.y = -b.velocity.y;
				}
				break;
			}
		}
	}

	private void doCollisionX(CollisionGrid collGrid, Body b, Transform t) {
		if (b.velocity.x == 0)
			return;

		int ix;
		if (b.velocity.x < 0) {
			ix = collGrid.indexXFromPos(t.position.x);
		} else {
			ix = collGrid.indexXFromPos(t.position.x + t.size.x);
		}

		int minIy = collGrid.indexYFromPos(t.position.y);
		int maxIy = collGrid.indexYFromPos(t.position.y + t.size.y);
		for (int y = minIy; y <= maxIy; ++y) {
			CollisionCell cell = collGrid.cells[ix][y];
			if (cell.solid) {
				if (b.velocity.x > 0) {
					t.position.x = collGrid.cellSize.x * ix - t.size.x - 1;
					b.velocity.x = 0;
				} else {
					t.position.x = collGrid.cellSize.x * (ix + 1);
					b.velocity.x = 0;
				}
				break;
			}
		}
	}

	private void doWindowCollision(Body b, Transform t) {
		if (t.position.y > Game.height - t.size.y) {
			t.position.y = Game.height - t.size.y;
			b.velocity.y = 0;
			b.onGround = true;
		}
	}

}
