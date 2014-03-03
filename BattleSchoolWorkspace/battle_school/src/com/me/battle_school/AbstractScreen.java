package com.me.battle_school;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class AbstractScreen implements Screen {

	protected final BattleSchool game;
	protected final BitmapFont font;
	protected final SpriteBatch batch;
	protected final Stage stage;
	
	private Skin skin; 
	private Table table; 
	
	public AbstractScreen(BattleSchool game) {
		this.game = game; 
		this.font = new BitmapFont();
		this.batch = new SpriteBatch(); 
		this.stage = new Stage(0, 0, true);
	}

	protected String getName() {
		return getClass().getSimpleName(); 
	}
	
	//**Screen Implementation **//
	
	@Override
	public void render(float delta) {
        // the following code clears the screen with the given RGB color (black)
        Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        // update and draw the stage actors
        stage.act( delta );
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(BattleSchool.LOG, "Resizing screen: " + getName() + " to: " + width + " x " + height);
		//resize stage
		stage.setViewport(width, height, true); 
	}

	@Override
	public void show() {
		Gdx.app.log(BattleSchool.LOG, "Showing screen: " + getName()); 
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		Gdx.app.log(BattleSchool.LOG, "Hiding screen: " + getName());
		dispose(); 
	}

	@Override
	public void pause() {
        Gdx.app.log( BattleSchool.LOG, "Pausing screen: " + getName() );
	}

	@Override
	public void resume() {
		Gdx.app.log( BattleSchool.LOG, "Resuming screen: " + getName() );

	}

	@Override
	public void dispose() {
        Gdx.app.log( BattleSchool.LOG, "Disposing screen: " + getName() );

		// dispose the collaborators
		stage.dispose();
		batch.dispose();
		font.dispose();
		if (skin != null) skin.dispose();
	}

	public Skin getSkin() {
		if (skin == null) {
			FileHandle skinFile = Gdx.files.internal("skin/uiskin.json");
			skin = new Skin(skinFile);
		}
		return skin;

//		// Store the default libgdx font under the name "default".
//		skin = new Skin(); 
//		// Generate a 1x1 white texture and store it in the skin named "white".
//		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
//		pixmap.setColor(Color.WHITE);
//		pixmap.fill();
//		skin.add("white", new Texture(pixmap));
//		
//		skin.add("default", new BitmapFont());
//
//		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
//		TextButtonStyle textButtonStyle = new TextButtonStyle();
//		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
//		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
//		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
//		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
//		textButtonStyle.font = skin.getFont("default");
//		skin.add("default", textButtonStyle);
//
//		// Create a table that fills the screen. Everything else will go inside this table.
//		return skin; 
	}

	public Table getTable() {
		if (table == null) {
			table = new Table(getSkin());
			table.setFillParent(true);
			if (BattleSchool.DEV_MODE) {
				table.debug();
			}
			stage.addActor(table);
		}
		return table;
	}

}
