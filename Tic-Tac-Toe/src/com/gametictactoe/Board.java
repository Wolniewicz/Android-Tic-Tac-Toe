package com.gametictactoe;
 
import android.content.Context;
import java.util.Random;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import java.lang.Runnable;
 

public class Board extends View{
 
	//set up some global variables.
	Paint paint;
	Paint paintX;
	Paint paintO;
	boolean isX;
	Context context1;
	boolean vsComp;
	boolean xSaved;
	
	public Board(Context context, boolean xFirst, boolean compOn) {
	    super(context);
	    
	    
	    // Declare paint for the tic-tac-toe grid
	    paint = new Paint();
	    paint.setColor(Color.WHITE);
	    paint.setStyle(Paint.Style.STROKE);
	   
	    // declare paint for drawing x's
	    paintX = new Paint();
	    paintX.setColor(Color.RED);
	    paintX.setStyle(Paint.Style.STROKE);
	 
	    // declare paint for drawing O's
	    paintO = new Paint();
	    paintO.setColor(Color.BLUE);
	    paintO.setStyle(Paint.Style.STROKE);
	    
	    // isX is the on/ off counter to see if the next tap draws
	    // a x or o
	    isX = xFirst;
	    xSaved = xFirst;
	    
	    // have to declare context here because context is imported from main.
	    context1 = context;
	    
	    vsComp = compOn;
	  
	    }
		
	//Declare all the variables to do a toast
    Toast toast;
	int duration = Toast.LENGTH_SHORT;
	String xWon = "X wins!";
	String oWon = "O wins!";
	String tie = "Tie!";
	
	// initiate the variables that will keep track of wins and ties.
	double xWins = 0;
	double oWins = 0;
	double ties = 0;
	
	Handler myHandler = new Handler();
	
	//Here's a runnable/handler combo
	private Runnable mMyRunnable = new Runnable()
	{
	public void run(){
	
		redrawBoard();
	}
	};
	
public void redrawBoard(){
	

	_logicalBoard.clear();
	_canvas.drawColor(Color.BLACK);
    drawBoard();
    drawWins();
    
    isX = xSaved;
}
	
	// following three functions do a toast based upon which ones called
	// also increment by one, which win counter gets it
	public void xWin(){
		xWins+=.5;
    	toast = Toast.makeText(context1, xWon, duration);
        toast.show();
        
        
       /*
        myHandler.postDelayed(new Runnable(){
        	public void run(){
        		
        		redrawBoardX();
        	}
        	
        }, 1000);*/
        
        myHandler.postDelayed(mMyRunnable, 1000);
        
        
       
        
     
      
    	}
	public void oWin(){
    	toast = Toast.makeText(context1, oWon, duration);
        toast.show();
        oWins+=.5;
        
        myHandler.postDelayed(mMyRunnable, 1000);
       
    	}
	public void Tie(){
    	toast = Toast.makeText(context1, tie, duration);
        toast.show();
        ties+=.5;
        
        myHandler.postDelayed(mMyRunnable, 1000);
 	
	
	}
  
	
	public void drawWins(){
		 
		 for(int i = 0; i < xWins; i++){
		        _canvas.drawText("|", 0, 1, ((32 + i)*_width/48),  (25*_height/32), paint);
		        }
		 for(int k = 0; k < oWins; k++){
		        _canvas.drawText("|", 0, 1, ((32 + k)*_width/48),  (26*_height/32), paint);
		        }
		for(int j = 0; j < ties; j++){
	        _canvas.drawText("|", 0, 1, ((32 + j)*_width/48),  (27*_height/32), paint);
	        }
	}
	// Draw the grid
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(_bitmap, 0, 0, paint);
    }

    
    // variables to draw the grid
    int _height;
    int _width;
    
    Bitmap _bitmap;
    Canvas _canvas;
     
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    	
    	// height and width of screen
        _height = View.MeasureSpec.getSize(heightMeasureSpec);
        _width = View.MeasureSpec.getSize(widthMeasureSpec);
     
        setMeasuredDimension(_width, _height);
        
        _bitmap = Bitmap.createBitmap(_width, _height, Bitmap.Config.ARGB_8888);
        _canvas = new Canvas(_bitmap);
        
        // draw board
        calculateLinePlacements();
        drawBoard();
    }
    
    Point[] _firstHorizontalLine;
    Point[] _secondHorizontalLine;
    Point[] _firstVerticalLine;
    Point[] _secondVerticalLine;
     
    
    private LogicalBoard _logicalBoard;
     
    private void calculateLinePlacements() {
    	// each variable is 1/3 of the screen
        int splitHeight = _height / 4;
        int splitWidth = _width / 3;
     
        _firstHorizontalLine = new Point[2];
        Point p1 = new Point(0, splitHeight);
        Point p2 = new Point(_width, splitHeight);
        _firstHorizontalLine[0] = p1;
        _firstHorizontalLine[1] = p2;
     
        _secondHorizontalLine = new Point[2];
        p1 = new Point(0, 2 * splitHeight);
        p2 = new Point(_width, 2 * splitHeight);
        _secondHorizontalLine[0] = p1;
        _secondHorizontalLine[1] = p2;
     
        _firstVerticalLine = new Point[2];
        p1 = new Point(splitWidth, 0);
        p2 = new Point(splitWidth, 3*_height/4);
        _firstVerticalLine[0] = p1;
        _firstVerticalLine[1] = p2;
     
        _secondVerticalLine = new Point[2];
        p1 = new Point(2 * splitWidth, 0);
        p2 = new Point(2 * splitWidth, 3*_height/4);
        _secondVerticalLine[0] = p1;
        _secondVerticalLine[1] = p2;
     
        _logicalBoard = new LogicalBoard(splitWidth, splitHeight);
    }
    
    private void drawBoard() {
    	//draw the lines
        _canvas.drawLine(_firstHorizontalLine[0].x, _firstHorizontalLine[0].y,
                _firstHorizontalLine[1].x, _firstHorizontalLine[1].y, paint);
     
        _canvas.drawLine(_secondHorizontalLine[0].x,
                _secondHorizontalLine[0].y, _secondHorizontalLine[1].x,
                _secondHorizontalLine[1].y, paint);
     
        _canvas.drawLine(_firstVerticalLine[0].x, _firstVerticalLine[0].y,
                _firstVerticalLine[1].x, _firstVerticalLine[1].y, paint);
     
        _canvas.drawLine(_secondVerticalLine[0].x, _secondVerticalLine[0].y,
                _secondVerticalLine[1].x, _secondVerticalLine[1].y, paint);
     
        _canvas.drawText("X wins:", 0, 6, _width/3,  (25*_height/32), paint);
        _canvas.drawText("O wins:", 0, 6, _width/3,  (26*_height/32), paint);
        _canvas.drawText("Ties:", 0, 4, _width/3,  (27*_height/32), paint);
        
   _canvas.save();
        
        invalidate();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	
 
    		if (event.getAction() == MotionEvent.ACTION_DOWN) {
    			RectF position = _logicalBoard.getPositionToFill(event.getX(),
    					event.getY(), isX);
    			if (position != null) {
    				// draw an X or an O here
    				if (isX) {
    					_canvas.drawLine(position.left, position.top,
    							position.right, position.bottom, paintX);
    					_canvas.drawLine(position.right, position.top,
    							position.left, position.bottom, paintX);
    					isX = !isX;
    					// always leave function if xWon, so thats why it returns true
    					invalidate();
    				     if(_logicalBoard.checkForWin() == 1){
    				        	xWin();
    				        	return true;
    				        }
    				        if(_logicalBoard.checkForWin() == 2){
    				        	oWin();
    				        	return true;
    				        }
    				        if(_logicalBoard.checkForWin() == 3){
    				        	Tie();
    				        	return true;
    				        }
    					// NEEDS TO BE OUT OF TOUCH
    					if(vsComp == true){
    			    			
    						
    						// Get the random values
    						final Random myRandom = new Random();
    						int random1 = myRandom.nextInt();
    						random1 =  (random1 % 3) + 1;
    					    if(random1 == 2){
    					    	random1 = 5;
    					    }
    						
    						final Random myRandom2 = new Random();
    						int random2 = myRandom2.nextInt();
    						random2 = random2 % _width + 1;
    						
    			        		 // Get that location on the map.
    			    		RectF positionComp = _logicalBoard.getPositionToFill(random2, random1*_height/8 , false);
    			    		//RectF positionComp= _logicalBoard.getPositionToFill(random1, random2, isX);
    			    			
    			    		// only actually draw there if it isn't in use yet
    			    		if(positionComp != null){
			    				_canvas.drawOval(positionComp, paintO);
			    				_canvas.drawOval(positionComp, paintO);
			    				_canvas.drawOval(positionComp, paintO);
			    				isX = !isX;
			    				} 
    			    			
    			    			// Does the same thing as above, but repeats until it finds a good spot,
    			    		// if a good spot was found then it never enters this loop.
    			    		while(positionComp == null){
    			    	
        						random1 =  (random1 %3) + 1;
        						  if(random1 == 2){
          					    	random1 = 5;
          					    }
        						random2 = random2 % _width + 1;
    			    			
    			    			positionComp = _logicalBoard.getPositionToFill(random2, random1*_height/8 , false);
    			    			if(positionComp != null){
    			    				_canvas.drawOval(positionComp, paintO);
    			    				isX = !isX;
    			    				} 
    			    			}
    			    		}
    					
    				} else {
    						
    				
    					_canvas.drawOval(position, paintO);
    					isX = !isX;
    					}
    				}
    				
    				invalidate();
    			}
    	
    		
        // based upon what it returns toast the right text
        if(_logicalBoard.checkForWin() == 1){
        	xWin();
        }
        if(_logicalBoard.checkForWin() == 2){
        	oWin();
        }
        if(_logicalBoard.checkForWin() == 3){
        	Tie();
        }
        return true;
    }

}
    
