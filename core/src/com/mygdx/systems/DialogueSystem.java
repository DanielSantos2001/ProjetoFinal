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
    private ArrayList<Dialogue> kingDialog;
    private TextureManager textureManager;
    private BitmapFont font;
    private final Circle kingCircle;
    public DialogueSystem() {
        kingCircle = new Circle(513.69653f, 1292.4524f, 10);
        kingDialog = new ArrayList<>();
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

    public void kingDialog(int count,KnightsOath game){
        if(count < kingDialog.size()){
            setDialog(kingDialog.get(count),game);
        }

    }
    public void setDialog(Dialogue dialogue, KnightsOath mainGame) {
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, dialogue.getMessage());
        dialogue.getNinePatch().draw(mainGame.batch, 475, 1210, 80, 30);
        font.getData().setScale(0.05f);
        font.draw(mainGame.batch, glyphLayout, 495, 1235);
        font.setColor(Color.BLACK);
    }

    public Circle getKingCircle(){return this.kingCircle;}
}
