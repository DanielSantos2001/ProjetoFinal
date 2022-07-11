package com.mygdx.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Circle;
import com.mygdx.UI.Dialogue;
import com.mygdx.animations.TextureManager;
import com.mygdx.game.KnightsOath;

import java.util.ArrayList;



public class DialogueSystem {
    private final ArrayList<Dialogue> kingDialog;
    private final ArrayList<Dialogue> armorSellerDialog;
    private final ArrayList<Dialogue> weaponsSellerDialog;
    private final ArrayList<Dialogue> potionsSellerDialog;
    private final TextureManager textureManager;
    private final BitmapFont font;
    private final Circle kingCircle;
    private final Circle armorSellerCircle;
    private final Circle weaponsSellerCircle;
    private final Circle potionsSellerCircle;
    public DialogueSystem() {
        kingCircle = new Circle(527.69653f, 1292.4524f, 10);
        armorSellerCircle = new Circle(615.8702f,390.90527f,5);
        weaponsSellerCircle = new Circle(775.6982f,390.90527f,5);
        potionsSellerCircle = new Circle(935.50244f,390.90527f,5);
        kingDialog = new ArrayList<>();
        armorSellerDialog = new ArrayList<>();
        weaponsSellerDialog = new ArrayList<>();
        potionsSellerDialog = new ArrayList<>();
        textureManager = new TextureManager();
        font = new BitmapFont(Gdx.files.internal("Textures/Skin/ui/font-title-export.fnt"), Gdx.files.internal("Textures/Skin/ui/font-title-export.png"), false);
    }

    public void addKingDialog(){
        kingDialog.add(new Dialogue(new NinePatch((textureManager.getDialogKingSheet())), "Hi Knight"));
        kingDialog.add(new Dialogue(new NinePatch((textureManager.getDialogKnightSheet())), "Hi my King"));
        kingDialog.add(new Dialogue(new NinePatch((textureManager.getDialogKingSheet())), "Evil never rests"));
        kingDialog.add(new Dialogue(new NinePatch((textureManager.getDialogKnightSheet())), "I will protect the \nkingdom at all costs"));
        kingDialog.add(new Dialogue(new NinePatch((textureManager.getDialogKingSheet())), "I trust you with all \nmy strength"));
        kingDialog.add(new Dialogue(new NinePatch((textureManager.getDialogKnightSheet())), "I will not disappoint \nyou"));
        kingDialog.add(new Dialogue(new NinePatch((textureManager.getDialogKingSheet())), "Go to the cemetery \nand kill the skeleton \nthat is after our \nvillagers"));
        kingDialog.add(new Dialogue(new NinePatch((textureManager.getDialogKnightSheet())), "Consider it done"));
    }

    public void addArmorSellerDialog(){
        armorSellerDialog.add(new Dialogue(new NinePatch((textureManager.getDialogArmorSellerSheet())), "Welcome to the \narmor shop Knight"));
        armorSellerDialog.add(new Dialogue(new NinePatch((textureManager.getDialogKnightSheet())), "Hi Armor Seller"));
        armorSellerDialog.add(new Dialogue(new NinePatch((textureManager.getDialogArmorSellerSheet())), "I sell the best \narmor in town"));
        armorSellerDialog.add(new Dialogue(new NinePatch((textureManager.getDialogKnightSheet())), "Let me have a look"));
    }

    public void addWeaponsSellerDialog(){
        weaponsSellerDialog.add(new Dialogue(new NinePatch((textureManager.getDialogWeaponsSellerSheet())), "Welcome to the \nweapon shop Knight"));
        weaponsSellerDialog.add(new Dialogue(new NinePatch((textureManager.getDialogKnightSheet())), "Hi Weapons Seller"));
        weaponsSellerDialog.add(new Dialogue(new NinePatch((textureManager.getDialogWeaponsSellerSheet())), "I sell the best \nweapons in town"));
        weaponsSellerDialog.add(new Dialogue(new NinePatch((textureManager.getDialogKnightSheet())), "Let me have a look"));
    }

    public void addPotionsSellerDialog(){
        potionsSellerDialog.add(new Dialogue(new NinePatch((textureManager.getDialogPotionsSellerSheet())), "Welcome to the \npotions shop Knight"));
        potionsSellerDialog.add(new Dialogue(new NinePatch((textureManager.getDialogKnightSheet())), "Hi Potions Seller"));
        potionsSellerDialog.add(new Dialogue(new NinePatch((textureManager.getDialogPotionsSellerSheet())), "I sell the best \npotions in town"));
        potionsSellerDialog.add(new Dialogue(new NinePatch((textureManager.getDialogKnightSheet())), "Let me have a look"));
    }
    public void kingDialog(int count,KnightsOath game){
        if(count < kingDialog.size()){
            setDialog(kingDialog.get(count),game,475,1210,495,1235);
        }
    }

    public void armorSellerDialog(int count,KnightsOath game){
        if(count < armorSellerDialog.size()/2){
            setDialog(armorSellerDialog.get(count),game,562,330,582,352);
        }
    }

    public void weaponsSellerDialog(int count,KnightsOath game){
        if(count < weaponsSellerDialog.size()/2){
            setDialog(weaponsSellerDialog.get(count),game,722,330,742,352);
        }
    }

    public void potionsSellerDialog(int count,KnightsOath game){
        if(count < potionsSellerDialog.size()/2){
            setDialog(potionsSellerDialog.get(count),game,882,330,902,352);
        }
    }

    public void setDialog(Dialogue dialogue, KnightsOath mainGame,float frameX,float frameY,float fontX,float fontY) {
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, dialogue.getMessage());
        dialogue.getNinePatch().draw(mainGame.batch, frameX, frameY, 80, 30);
        font.getData().setScale(0.05f);
        font.draw(mainGame.batch, glyphLayout, fontX, fontY);
        font.setColor(Color.BLACK);
    }

    public Circle getKingCircle(){return this.kingCircle;}
    public Circle getArmorSellerCircle(){return this.armorSellerCircle;}
    public Circle getWeaponsSellerCircle(){return this.weaponsSellerCircle;}
    public Circle getPotionsSellerCircle(){return this.potionsSellerCircle;}
}
