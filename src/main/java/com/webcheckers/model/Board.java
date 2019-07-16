package com.webcheckers.model;

import com.webcheckers.ui.Piece;

import java.lang.reflect.Array;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.util.ArrayList;
import java.util.Map;

import com.webcheckers.appl.PlayerServices;
/**
 * A class to represent the checkers board for a Game.
 */

public class Board {

    private Square[][] board;

    private Piece.Color activeColor;

    private ArrayList<Move> moveList;
	
	private int whitePieces;
	private int redPieces;

    public Board() {
        this.board = new Square[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // if row is even, white first
                if (row % 2 == 0) {
                    if (col % 2 == 0) {
                        board[row][col] = new Square(Square.Color.WHITE);
                    } else {
                        board[row][col] = new Square(Square.Color.BLACK);
                        if (row <= 2) {
                            board[row][col].addPiece(Piece.Color.WHITE);
                        } else if (row >= 5) {
                            board[row][col].addPiece(Piece.Color.RED);
                        }
                    }
                } else {
                    if (col % 2 == 0) {
                        board[row][col] = new Square(Square.Color.BLACK);
                        if (row <= 2) {
                            board[row][col].addPiece(Piece.Color.WHITE);
                        } else if (row >= 5) {
                            board[row][col].addPiece(Piece.Color.RED);
                        }
                    } else {
                        board[row][col] = new Square(Square.Color.WHITE);
                    }
                }
            }
        }
        this.activeColor = Piece.Color.RED;
		this.whitePieces = 12;
		this.redPieces = 12;
		this.moveList = new ArrayList<>();
    }

	/**
	 * A getter method for the whitePieces that is used for testing the capturePieces methods
	 * @return int the number of white pieces left on the board
	 */
	public int getWhitePieces() {
		return whitePieces;
	}

	/**
	 * A getter method for the redPieces that is used for testing the capturePieces methods
	 * @return int the number of red pieces left on the board
	 */
	public int getRedPieces() {
		return redPieces;
	}

	public Piece.Color getActiveColor() {
        return this.activeColor;
    }

    public void changeActiveColor(Piece.Color activeColor) {
        this.activeColor = activeColor;
    }

	//This will flip the move if it is from white so that way it matches our board model's actual squares.
	public Move getActualMove(Move move) {
		//If the piece is red, don't flip it.
		if (this.activeColor == Piece.Color.RED)
			return move;
		
		Position start = move.getStart();
        int startRow = 7 - start.getRow();
        int startCol = 7 - start.getCell();
		Position end = move.getEnd();
        int endRow = 7 - end.getRow();
        int endCol = 7 - end.getCell();
		Position actualStart = new Position(startRow, startCol);
		Position actualEnd = new Position(endRow, endCol);
		Move actualMove = new Move(actualStart, actualEnd);
		return actualMove;
	}

    public boolean isValidMove(Move move) {
		Move actualMove = getActualMove(move);
		
        Position start = actualMove.getStart();
        Position end = actualMove.getEnd();
        int endRow = end.getRow();
        int endCol = end.getCell();
        Square endSquare = this.board[endRow][endCol];

        // is the move onto a valid square
        boolean validSquare = endSquare.isPlayable();
		//End is the same place as the inititial start
		boolean isBackOnSameTile = this.board[endRow][endCol].getPiece() == getMovingPiece(move);
		//
		boolean actuallyIsValid = isActuallyValid(getMovingPiece(actualMove), start, end);

        if ((validSquare || isBackOnSameTile) && actuallyIsValid) {
			if (!this.moveList.isEmpty()) {
				Move recentMove = this.moveList.get(this.moveList.size() - 1);
				Position recentStart = recentMove.getStart();
				Position recentEnd = recentMove.getEnd();
				if (isJump(recentStart, recentEnd, false) && !isJump(start, end, true)) {
					return false;
				} else if (isDiagonal(recentStart, recentEnd)) {
					return false;
				}
			}
            this.moveList.add(actualMove);
            return true;
        } else {
            return false;
        }

    }
	
	private Piece getMovingPiece(Move move) {
		Move firstMove;
		if (this.moveList.isEmpty()) {
			if (move == null) {
				return null;
			}
			firstMove = move;
		} else {
			firstMove = this.moveList.get(0);
		}
		int firstRow = firstMove.getStart().getRow();
		int firstCol = firstMove.getStart().getCell();
		return board[firstRow][firstCol].getPiece();
	}
	
	private boolean isActuallyValid(Piece movingPiece, Position start, Position end) {
		boolean diagonalMove = isDiagonal(start, end);
		boolean jumpMove = isJump(start, end, true);
		int startRow = start.getRow();
        int endRow = end.getRow();
		//Do check for king stuff
		if (movingPiece.getType() == Piece.Type.KING) {
			return diagonalMove || jumpMove;
		} else {
			if (diagonalMove) {
				if (movingPiece.getColor() == Piece.Color.RED) {
					if (startRow - endRow != 1) {
						return false;
					} else {
						return true;
					}
				} else {
					if (startRow - endRow != -1) {
						return false;
					} else {
						return true;
					}
				}
			} else if (jumpMove) {
				if (movingPiece.getColor() == Piece.Color.RED) {
					if (startRow - endRow != 2) {
						return false;
					} else {
						return true;
					}
				} else {
					if (startRow - endRow != -2) {
						return false;
					} else {
						return true;
					}
				}
			} else {
				return false;
			}
		}
	}

    private boolean isJump(Position start, Position end, boolean isNewMove){
		int xMovement = start.getCell() - end.getCell();
        int yMovement = start.getRow() - end.getRow();
        if (Math.abs(xMovement) != 2) {
			return false;
		}
		if (Math.abs(yMovement) != 2) {
			return false;
		}
		int jumpedOverCol = start.getCell() - (xMovement/2);
		int jumpedOverRow = start.getRow() - (yMovement/2);
		boolean jumpedOverNonNull = board[jumpedOverRow][jumpedOverCol].getPiece() != null;
		boolean jumpedPieceIsOppositeColor = false;
		boolean hasJumpedBefore = false;
		if (isNewMove) {
			hasJumpedBefore = jumpedBefore(jumpedOverRow, jumpedOverCol);
		}
		// if a piece was jumped over, then check its color
		if (jumpedOverNonNull) {
			jumpedPieceIsOppositeColor = board[jumpedOverRow][jumpedOverCol].getPiece().getColor() != this.activeColor;
		}
		if (jumpedOverNonNull && jumpedPieceIsOppositeColor && !hasJumpedBefore) {
			int endCol = end.getCell();
			int endRow = end.getRow();
			return this.board[endRow][endCol].getPiece() == null 
				   || this.board[endRow][endCol].getPiece() == getMovingPiece(new Move(start,end));
		}
		return false;
    }
	
	private boolean jumpedBefore(int jumpedOverRow, int jumpedOverCol) {
		for (Move move : this.moveList) {
			Position start = move.getStart();
			Position end = move.getEnd();
			int xMovement = start.getCell() - end.getCell();
			int yMovement = start.getRow() - end.getRow();
			if (Math.abs(xMovement) != 2) {
				continue;
			}
			if (Math.abs(yMovement) != 2) {
				continue;
			}
			int otherJumpedOverCol = start.getCell() - (xMovement/2);
			int otherJumpedOverRow = start.getRow() - (yMovement/2);
			if  (jumpedOverRow == otherJumpedOverRow && jumpedOverCol == otherJumpedOverCol) {
				return true;
			}
		}
		return false;
	}

    private boolean isDiagonal(Position start, Position end) {
        int xMovement = start.getCell() - end.getCell();
        int yMovement = start.getRow() - end.getRow();
        if (Math.abs(xMovement) != 1) {
            return false;
        }
        if (Math.abs(yMovement) != 1) {
            return false;
        }
        return true;
    }

    public String backUpMove() {
        // do some further checks on move
        // check for multi jumps and stuff
        boolean canBackUp = true; // change this flag if not possible
        if (canBackUp) {
			if (this.moveList.size() > 0) {
				this.moveList.remove(this.moveList.size() - 1);
				return "true";
			}
			else {
				return "moveList is empty, cannot backup the move.";
			}
        } else {
            String errorMessage = ""; // provide error message if not possible
            return errorMessage;
        }
    }

    public Square[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getColor() == Square.Color.BLACK) {
                    if (board[i][j].getPiece() != null) {
                        s += "[(B)]";
                    } else {
                        s += "[B]";
                    }
                } else {
                    s += "[W]";
                }
            }
            s += "\n";
        }
        return s;
    }
	
	public void checkMakeKing() {
		for (int i = 0; i < 8; i++) {
			Piece current = this.board[0][i].getPiece();
			if (current != null) {
				if (current.getType() != Piece.Type.KING && current.getColor() == Piece.Color.RED) {
					current.makeKing();
				}
			}
			current = this.board[7][i].getPiece();
			if (current != null) {
				if (current.getType() != Piece.Type.KING && current.getColor() == Piece.Color.WHITE) {
					current.makeKing();
				}
			}
		}
	}
	
	public void capturePieces() {
		for (Move move : this.moveList) {
			Position start = move.getStart();
			Position end = move.getEnd();
			int xMovement = start.getCell() - end.getCell();
			int yMovement = start.getRow() - end.getRow();
			if (Math.abs(xMovement) != 2) {
				continue;
			}
			if (Math.abs(yMovement) != 2) {
				continue;
			}
			int jumpedOverCol = start.getCell() - (xMovement/2);
			int jumpedOverRow = start.getRow() - (yMovement/2);
			capturePiece(jumpedOverRow, jumpedOverCol);
		}
	}
	
	public void capturePiece(int row, int col) {
		if (this.board[row][col].getPiece() == null) {
			return;
		}
		Piece.Color color = this.board[row][col].getPiece().getColor();
		if (color == Piece.Color.RED) {
			this.redPieces--;
		} else {
			this.whitePieces--;
		}
		System.out.println("Red pieces: " + redPieces + " White pieces: " + whitePieces);
		this.board[row][col].removePiece();
	}
	
	public boolean teamIsEliminated() {
		return whitePieces == 0 || redPieces == 0;
	}
	
	public boolean resetMoves(PlayerServices player) {
		if (player.getCurrentGame().getPlayer1().Id().equals(player.Id())) {
			if (this.activeColor == Piece.Color.WHITE) {
				return false;
			}
		} else {
			if (this.activeColor == Piece.Color.RED) {
				return false;
			}
		}
		this.moveList.clear();
		return true;
	}
	
	private boolean tookAllJumps() {
		boolean mustJump = checkForJumps();
		if (this.moveList.size() > 0) {
			Move firstMove = this.moveList.get(0);
			if (mustJump && !isJump(firstMove.getStart(), firstMove.getEnd(), false)) {
				return false;
			}
		}
		return true;
	}
	
	//This should be what makes sure all the moves currently in move list are
	//actually allowed in that order and this should also submit those changes
	//to the game board. The string returned should be "true" if it was successful
	//and what was wrong with the move if it was not successful
	public String trySubmitTurn() {
    	//TODO force user to execute jump moves
        // create a list of Moves that are jump moves
        // if this list size != 0, then the stored move must be in the list
		//Right now this defaults to true. Logic should be added to make sure that 
		//all the moves in the move list are valid.
		if (tookAllJumps()) {
			if (this.moveList.size() > 0) {
				capturePieces();
				Position pieceInitialPosition = this.moveList.get(0).getStart();
				Position pieceEndingPosition = this.moveList.get(this.moveList.size() - 1).getEnd();
				int initialRow = pieceInitialPosition.getRow();
				int initialCol = pieceInitialPosition.getCell();
				Piece initialPiece = this.board[initialRow][initialCol].getPiece();
				int endRow = pieceEndingPosition.getRow();
				int endCol = pieceEndingPosition.getCell();
				this.board[endRow][endCol].addPiece(initialPiece.getColor());
				if (initialPiece.getType() == Piece.Type.KING) {
					this.board[endRow][endCol].getPiece().makeKing();
				}
				if (initialRow != endRow || initialCol != endCol) {
					this.board[initialRow][initialCol].removePiece();
				}
			}
			else {
				return "No moves!";
			}
			//The turn was submitted correctly!
			moveList.clear();
			return "true";
		}
		return "Invalid move! Did not take a required jump move.";
	}

	private boolean checkForJumps() {
        ArrayList<Move> possibleMoves = createPossibleJumpList(this.activeColor);
        if (possibleMoves.size() == 0) {
			return false;
		} else {
        	return true;
		}
    }

    private ArrayList<Move> createPossibleJumpList(Piece.Color color) {
    	ArrayList<Move> possibleMoves = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square s = this.board[row][col];
                if (s.getPiece() != null) {
                    if (s.getPiece().getColor() == color) {
						if (s.getPiece().getType() == Piece.Type.KING) {
							possibleMoves.addAll(checkForKingJumps(row, col));
						} else {
							if (this.activeColor == Piece.Color.RED) {
								possibleMoves.addAll(checkForRedJumps(row, col));
							} else {
								possibleMoves.addAll(checkForWhiteJumps(row, col));
							}
						}
                    }
                }
            }
        }
        return possibleMoves;
    }

    private ArrayList<Move> checkForKingJumps(int row, int col) {
    	ArrayList<Move> possibleJumps = new ArrayList<>();
    	Square s = this.board[row][col];

    	// check North-East move
    	Square ne = null;
    	if (row >= 2 && col <= 5) {
    		ne = this.board[row - 1][col + 1];
    		// if NE has a piece
    		if (ne.getPiece() != null) {
    			// if the piece is opposite color
    			if (ne.getPiece().getColor() != this.activeColor) {
    				// if the landing piece (2x2 jump) is empty
    				if (this.board[row - 2][col + 2].getPiece() == null) {
    					// then there is a jump
						Move m = new Move(new Position(row, col), new Position(row - 2,col + 2));
						possibleJumps.add(m);
					}
				}
			}
		}

    	// check South-East move
    	Square se = null;
    	if (row <= 5 && col <= 5) {
    		se = this.board[row + 1][col + 1];
			// if SE has a piece
			if (se.getPiece() != null) {
				// if the piece is opposite color
				if (se.getPiece().getColor() != this.activeColor) {
					// if the landing piece (2x2 jump) is empty
					if (this.board[row + 2][col + 2].getPiece() == null) {
						// then there is a jump
						Move m = new Move(new Position(row, col), new Position(row + 2,col + 2));
						possibleJumps.add(m);
					}
				}
			}
		}

    	// check South-West move
    	Square sw = null;
    	if (row <= 5 && col >= 2) {
    		sw = this.board[row + 1][col - 1];
			// if SW has a piece
			if (sw.getPiece() != null) {
				// if the piece is opposite color
				if (sw.getPiece().getColor() != this.activeColor) {
					// if the landing piece (2x2 jump) is empty
					if (this.board[row + 2][col - 2].getPiece() == null) {
						// then there is a jump
						Move m = new Move(new Position(row, col), new Position(row + 2,col - 2));
						possibleJumps.add(m);
					}
				}
			}
		}

    	// check North-West move
    	Square nw = null;
    	if (row >= 2 && col >= 2) {
    		nw = this.board[row - 1][col - 1];
			// if NW has a piece
			if (nw.getPiece() != null) {
				// if the piece is opposite color
				if (nw.getPiece().getColor() != this.activeColor) {
					// if the landing piece (2x2 jump) is empty
					if (this.board[row - 2][col - 2].getPiece() == null) {
						// then there is a jump
						Move m = new Move(new Position(row, col), new Position(row - 2,col - 2));
						possibleJumps.add(m);
					}
				}
			}
		}
    	return possibleJumps;
	}

	private ArrayList<Move> checkForRedJumps(int row, int col) {
    	ArrayList<Move> possibleJumps = new ArrayList<>();
    	Square s = this.board[row][col];

		// check North-East move
		Square ne = null;
		if (row >= 2 && col <= 5) {
			ne = this.board[row - 1][col + 1];
			// if NE has a piece
			if (ne.getPiece() != null) {
				// if the piece is opposite color
				if (ne.getPiece().getColor() != this.activeColor) {
					// if the landing piece (2x2 jump) is empty
					if (this.board[row - 2][col + 2].getPiece() == null) {
						// then there is a jump
						Move m = new Move(new Position(row, col), new Position(row - 2,col + 2));
						possibleJumps.add(m);
					}
				}
			}
		}

		// check North-West move
		Square nw = null;
		if (row >= 2 && col >= 2) {
			nw = this.board[row - 1][col - 1];
			// if NW has a piece
			if (nw.getPiece() != null) {
				// if the piece is opposite color
				if (nw.getPiece().getColor() != this.activeColor) {
					// if the landing piece (2x2 jump) is empty
					if (this.board[row - 2][col - 2].getPiece() == null) {
						// then there is a jump
						Move m = new Move(new Position(row, col), new Position(row - 2,col - 2));
						possibleJumps.add(m);
					}
				}
			}
		}
		return possibleJumps;
	}

	private ArrayList<Move> checkForWhiteJumps(int row, int col) {
    	ArrayList<Move> possibleJumps = new ArrayList<>();
		// check South-East move
		Square se = null;
		if (row <= 5 && col <= 5) {
			se = this.board[row + 1][col + 1];
			// if SE has a piece
			if (se.getPiece() != null) {
				// if the piece is opposite color
				if (se.getPiece().getColor() != this.activeColor) {
					// if the landing piece (2x2 jump) is empty
					if (this.board[row + 2][col + 2].getPiece() == null) {
						// then there is a jump
						Move m = new Move(new Position(row, col), new Position(row + 2,col + 2));
						possibleJumps.add(m);
					}
				}
			}
		}

		// check South-West move
		Square sw = null;
		if (row <= 5 && col >= 2) {
			sw = this.board[row + 1][col - 1];
			// if SW has a piece
			if (sw.getPiece() != null) {
				// if the piece is opposite color
				if (sw.getPiece().getColor() != this.activeColor) {
					// if the landing piece (2x2 jump) is empty
					if (this.board[row + 2][col - 2].getPiece() == null) {
						// then there is a jump
						Move m = new Move(new Position(row, col), new Position(row + 2,col - 2));
						possibleJumps.add(m);
					}
				}
			}
		}
		return possibleJumps;
	}
}
