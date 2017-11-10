package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.Board;
import model.PlayResult;

class MyPanel extends JPanel {
	private Board board;
	MyPanel my = this;
	public MyPanel() {
		board = new Board();
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int x = (e.getX() - dx) / size;
				int y = (e.getY() - dy) / size;
				PlayResult pr = board.play(board.getCurrentPlayer(), x, y);
				my.repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
    private final int size = 50, dx = 50, dy = 100;
    private final int pieceSize = 40;
    private String getName(int player) {
    	switch(player) {
    	case 1:
    		return "Black";
    	case 2:
    		return "White";
    	default:
    		return "";
    	}
    }
    public void paint(Graphics g)  
    {  
        super.paint(g);   

		g.drawString("Current Player: " + getName(board.getCurrentPlayer()), 50, 75);
		if(board.getCurrentPlayer() == 1) {
			g.setColor(Color.BLACK);
		}else if(board.getCurrentPlayer() == 2){
			g.setColor(Color.WHITE);
		}
		if(board.getCurrentPlayer() != 0) {
			g.fillOval(200, 50, pieceSize, pieceSize);
		}else {
			g.drawString("Winner: " + getName(board.getWinner()), 100, 40);
		}

        g.setColor(Color.BLACK);
        for(int i = 1; i <= board.getWidth() - 1; i++) {
        	g.drawLine(dx + size * i, dy, dx + size * i, dy + size * board.getHeight());
        }
        for(int i = 1; i <= board.getHeight() - 1; i++) {
        	g.drawLine(dx, dy + size * i, dx + size * board.getWidth(), dy + size * i);
        }
        for(int i = 0; i < board.getWidth(); i++) {
        	for(int j = 0; j < board.getHeight(); j++) {
        		int player = board.getPos(i, j);
        		if(player == 1) {
        			g.setColor(Color.BLACK);
        		}else if(player == 2){
        			g.setColor(Color.WHITE);
        		}
        		if(player != 0) {
        			g.fillOval(dx + size * i + (size - pieceSize) / 2, dy + size * j + (size - pieceSize) / 2, pieceSize, pieceSize);
        		}
        	}
        }
    }
}

public class MainWindow extends JFrame{

	public MainWindow() {
//		Container c = getContentPane();
//		buildToolbar(c);
//		handleEvents();
//		c.add(da, BorderLayout.CENTER);
		setSize(500, 580);
		add(new MyPanel());
		setVisible(true);
//		setBackground(Color.BLUE);
	}
	
	
	public static void main(String args[]) {
		new MainWindow();
	}
	
	
}
