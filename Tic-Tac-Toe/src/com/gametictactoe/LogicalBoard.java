package com.gametictactoe;

import android.graphics.RectF;


public class LogicalBoard{
    private int _splitWidth;
    private int _splitHeight;
    

    

 
    public LogicalBoard(int splitWidth, int splitHeight) {
        _splitWidth = splitWidth;
        _splitHeight = splitHeight;
        SetupPositions();
    }
    
 
    
    private class BoardPosition extends RectF {
        public BoardPosition(float left, float top, float right, float bottom) {
            super(left, top, right, bottom);
            
            //each position starts empty
            
            filled = false;
            filledX = false;
            filledY = false;
        }
     
        private boolean filled;
        private boolean filledX;
        private boolean filledY;
     
        
        /*
         * never is used, but code saved incase
        public void setFilled(boolean filled) {
            this.filled = filled;
        }
     
        public boolean isFilled() {
            return filled;
        }
        */
       
    }
    
    BoardPosition[] _positions;
    
    private void SetupPositions() {
    	// declares where the 9 positions (0-8) are
        _positions = new BoardPosition[9];
        // first row
        _positions[0] = new BoardPosition(0, 0, _splitWidth, _splitHeight);
        _positions[1] = new BoardPosition(_splitWidth, 0, 2 * _splitWidth,
                _splitHeight);
        _positions[2] = new BoardPosition(2 * _splitWidth, 0, 3 * _splitWidth,
                _splitHeight);
        // second row
        _positions[3] = new BoardPosition(0, _splitHeight, _splitWidth,
                2 * _splitHeight);
        _positions[4] = new BoardPosition(_splitWidth, _splitHeight,
                2 * _splitWidth, 2 * _splitHeight);
        _positions[5] = new BoardPosition(2 * _splitWidth, _splitHeight,
                3 * _splitWidth, 2 * _splitHeight);
        // third row
        _positions[6] = new BoardPosition(0, 2 * _splitHeight, _splitWidth,
                3 * _splitHeight);
        _positions[7] = new BoardPosition(_splitWidth, 2 * _splitHeight,
                2 * _splitWidth, 3 * _splitHeight);
        _positions[8] = new BoardPosition(2 * _splitWidth, 2 * _splitHeight,
                3 * _splitWidth, 3 * _splitHeight);
    }
    
    public RectF getPositionToFill(float x, float y, boolean isX) {
        for (BoardPosition bp : _positions) {
        	//fills the box
            if (bp.contains(x, y) && !bp.filled) {
                RectF toReturn = new RectF(bp);
                bp.filled = true;
                //Lets us know if the spot contains an x or a y
                if(isX == true)
                	bp.filledX = true;
                if(isX == false)
                	bp.filledY = true;
                return toReturn;
            }
        }
     
        return null;
    }
    
 
    
    public int checkForWin() {
    	//check for win 0, 1, 2
    	if(_positions[0].filledX == true && _positions[1].filledX == true && _positions[2].filledX == true){
    		return 1;
    	}
    	
    	if(_positions[0].filledY == true && _positions[1].filledY == true && _positions[2].filledY == true){
    		return 2;
    	}
    	//check for win 3, 4, 5
    	if(_positions[3].filledX == true && _positions[4].filledX == true && _positions[5].filledX == true){
		return 1;
    	}
	
    	if(_positions[3].filledY == true && _positions[4].filledY == true && _positions[5].filledY == true){
		return 2;
    	}
	
    	
    	//check for win 6, 7, 8
    	if(_positions[6].filledX == true && _positions[7].filledX == true && _positions[8].filledX == true){
    		return 1;
    	}

    	if(_positions[6].filledY == true && _positions[7].filledY == true && _positions[8].filledY == true){
    		return 2;
    	}
    	
    	
    	// check for win 0, 3, 6
    	if(_positions[0].filledX == true && _positions[3].filledX == true && _positions[6].filledX == true){
    		return 1;
    	}

    	if(_positions[0].filledY == true && _positions[3].filledY == true && _positions[6].filledY == true){
    		return 2;
    	}
    	// check for win 1, 4, 7
    	if(_positions[1].filledX == true && _positions[4].filledX == true && _positions[7].filledX == true){
    		return 1;
    	}

    	if(_positions[1].filledY == true && _positions[4].filledY == true && _positions[7].filledY == true){
    		return 2;
    	}   	
    	// check for win 2, 5, 8 
    	if(_positions[2].filledX == true && _positions[5].filledX == true && _positions[8].filledX == true){
    		return 1;
    	}

    	if(_positions[2].filledY == true && _positions[5].filledY == true && _positions[8].filledY == true){
    		return 2;
    	}   	
    	// check for win 0, 4, 8
    	if(_positions[0].filledX == true && _positions[4].filledX == true && _positions[8].filledX == true){
    		return 1;
    	}	

    	if(_positions[0].filledY == true && _positions[4].filledY == true && _positions[8].filledY == true){
    		return 2;
    	}   	
    	// check for win 2, 4, 6
    	if(_positions[2].filledX == true && _positions[4].filledX == true && _positions[6].filledX == true){
    		return 1;
    	}

    	if(_positions[2].filledY == true && _positions[4].filledY == true && _positions[6].filledY == true){
    		return 2;
    	}
    	// check for all filled == tie
    	if(_positions[0].filled == true && _positions[1].filled == true && _positions[2].filled == true && _positions[3].filled == true 
    			&& _positions[4].filled == true && _positions[5].filled == true && _positions[6].filled == true && _positions[7].filled == true 
    			&& _positions[8].filled == true){
    		return 3;
    		
    	}
		return 0;
    }
    
    void clear(){
    	for(int i = 0; i < 9; i++){
    		// Clears the board for each spot of the board
    	_positions[i].filled = false;
    	_positions[i].filledX = false;
    	_positions[i].filledY = false;
    	}
    	
    }

}

