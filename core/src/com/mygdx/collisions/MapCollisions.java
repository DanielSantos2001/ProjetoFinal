package com.mygdx.collisions;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.camera.CameraManager;

public class MapCollisions {
    private TiledMapTileLayer collisionLayer;

    public MapCollisions(CameraManager cameraManager){
        collisionLayer = (TiledMapTileLayer) cameraManager.getMap().getLayers().get(0);
    }

    public boolean collidesRight(float knightX,float knightY) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (knightX / collisionLayer.getTileWidth()) + 2, (int) (knightY / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }

    public boolean collidesLeft(float knightX,float knightY) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (knightX / collisionLayer.getTileWidth()) + 1, (int) (knightY / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }

    public boolean collidesTop(float knightX,float knightY) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (knightX / collisionLayer.getTileWidth()) + 2, (int) (knightY / collisionLayer.getTileHeight()) + 1);
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }

    public boolean collidesBottom(float knightX,float knightY) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (knightX / collisionLayer.getTileWidth()) + 1, (int) (knightY / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }
}
