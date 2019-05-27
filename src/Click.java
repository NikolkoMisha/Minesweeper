//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//public class Click implements MouseListener{
//		
//	GUI gui = new GUI();
//	
//		@Override
//		public void mouseClicked(MouseEvent e) {
//		
//			gui.mx = e.getX();
//			gui.my = e.getY();  
//			
////			if(inBoxX() != -1  && inBoxY() != -1){
////				revealed[inBoxX()][inBoxY()] = true;
////			}
//
////			if(mx >= minusX+20 && mx < minusX+60 && my >= minusY+20 && my < minusY+60){
////				spacing--;
////				if(spacing < 1){
////					spacing = 1;
////				}
////			} else if(mx >= plusX+20 && mx < plusX+60 && my >= plusY+20 && my < plusY+60){
////				spacing++;
////				if(spacing > 15){
////					spacing = 15;
////				}
////			}
//			
//			
//			if(gui.inBoxX() != -1  && gui.inBoxY() != -1){
//				System.out.println(" The mouse is the [" + (gui.inBoxX()+1) +", " + (gui.inBoxY()+1)+"], Number of mine neighs : ["+ gui.neightbours[gui.inBoxX()][gui.inBoxY()] + "]");
//				if(gui.flagger == true && gui.revealed[gui.inBoxX()][gui.inBoxY()] == false){
//					if(gui.flagged[gui.inBoxX()][gui.inBoxY()] == false){
//					if(gui.count > 0){
//						gui.flagged[gui.inBoxX()][gui.inBoxY()] = true;
//						gui.count--;
//					}
//					} else{
//						gui.flagged[gui.inBoxX()][gui.inBoxY()] = false;
//						gui.count++;
//					}
//				} else {
//					if(gui.flagged[gui.inBoxX()][gui.inBoxY()] == false){
//						gui.revealed[gui.inBoxX()][gui.inBoxY()] = true;
//					}
//				}
//			} else { 
//				System.out.println("The pointer is not inside the any box");
//			} 
//			
//			if(gui.inSmiley()== true){
//				gui.resetAll();
//			}
//			
//			if(gui.inFlagger() == true){
//				if(gui.flagger == false){
//					gui.flagger = true;
//					System.out.println("In flagger = true!");
//				} else {
//					gui.flagger = false;
//					System.out.println("In flagger = false!");
//				}
//			}
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void mouseExited(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void mousePressed(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void mouseReleased(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//	}