import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;




import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game extends JFrame implements KeyListener{
	

	private static final long serialVersionUID = 1L; //viadice do eclipse
	
	
	public Stage stage;
	public static Ship player = new Ship(2,2);
	
	public Game(Stage stage){
		this.stage = stage;
		setTitle("Giulios");
		add(stage);
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(645,500);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		this.addKeyListener(new KeyListener(){
			
			@Override
			public void keyPressed(KeyEvent key) {
				switch (key.getKeyCode()) {
				case KeyEvent.VK_UP:
					player.moveY(-3);
					break;
				case KeyEvent.VK_DOWN:
					player.moveY(3);
					break;
				case KeyEvent.VK_LEFT:
					player.moveX(-3);
					break;
				case KeyEvent.VK_RIGHT:
					if(player.hasTurbo()){
						player.moveX(6);
						player.setFireImage("res//fire.png");
					}
					player.moveX(3);
					break;
				case KeyEvent.VK_SPACE:
					player.shoot();
					break;
				case KeyEvent.VK_R:
					player.setAmmoCount(20);
					break;
				case KeyEvent.VK_T:
					player.setHasTurbo(true);
					player.setFireImage("res//fire.png");
					break;
				default:
					break;
				}
				
			}

			public void keyReleased(KeyEvent e) {
				player.setFireImage("");
			}
			public void keyTyped(KeyEvent e) {}
			
		});
	}

	
	public static void main(String[] args){
		List enemies = new List();
		for(int i = 0; i < 20;i++){
			int posx = new Random().nextInt(500);
			int posy = new Random().nextInt(500);
			enemies.add(new Enemy(posx,posy));
		}
		Stage fase = new Stage(player,enemies);
		new Game(fase);
		Timer timer = new Timer();  
		
		
		timer.scheduleAtFixedRate(  
		        new TimerTask() {  
		            public void run() {  
		            	if(player.getHealth() <= 0){
		            		//System.exit(0);
		            		this.cancel();
		            		
		            	}
		            	for(int i = 0; i < enemies.size();i++){
		       
		            		((Enemy) enemies.get(i)).move();
		            		
		            		if(player.collidedWith((Object)enemies.get(i))){
		        				((Enemy) enemies.get(i)).setVisible(false);
		        				enemies.remove(i);
		        				player.setHealth(player.getHealth() - 10);
		        				if(player.getScore() > 0){
		        					player.setScore(player.getScore() - 10);
		        				}
		        			}
		            	}
		            	
		            	for(int i = 0; i < player.getAmmo().size();i++){
		            		Ammo m = (Ammo) player.getAmmo().get(i);
		            		if(m.getX() > 400){
		            			player.getAmmo().remove(i);
		            		}
		            	}
		            	for(int i = 0; i < player.getAmmo().size();i++){
		            		((Ammo) player.getAmmo().get(i)).move();
		            		for(int j = 0; j < enemies.size();j++){
		            			if(((Ammo) player.getAmmo().get(i)).collidedWith((Object) enemies.get(j))){
		            				player.getAmmo().remove(i);
		            				player.setScore(player.getScore() + 10);
		            				enemies.remove(j);
		            				j = enemies.size(); //Se player.getAmmo().get(i) colidiu com algum objeto,então não é necessário
		            									//verificar se colidiu com mais algum
		            			}
		            				
		            		}
		            	}
		            	
		            	fase.repaint();
		            	System.out.println(player.getAmmo().toString());
		            }  
		        }, 1000, 10);  
	}
	
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
