package TalabiaChessWales;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import TalabiaChessWales.controller.PieceController;
import TalabiaChessWales.factory.PieceFactory;
import TalabiaChessWales.model.Piece;

public class SaveLoad {
    public static void saveGame() {
        ChessBoard board = MainMenu.board;

        try {
            FileWriter writer = new FileWriter("game_info_save.txt");
            
            String[] data = {
                    "current_player:" + board.currentPlayer,
                    "switch_turn_check:" + board.switch_turn_check
            };

            writer.write(String.join(",", data) + "\n");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            FileWriter writer = new FileWriter("game_board_save.txt");
            PieceController[][] pieces = board.get_board();
            for (int y = 0; y < pieces.length; y++) {
                PieceController[] pieceRow = pieces[y];
                for (int x = 0; x < pieceRow.length; x++) {
                    if (pieceRow[x] != null) { // Check if the piece is not null
                        Piece piece = pieceRow[x].get_model();

                        String[] data = {
                                "index:" + piece.getPlayerIndex(),
                                "type:" + piece.getPieceType().name(),
                                "x:" + x,
                                "y:" + y
                        };

                        writer.write(String.join(",", data) + "\n");
                    }
                }
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean load_board_data(ChessBoard board) {
        File file = new File("game_info_save.txt");
        if (!file.exists()) {
            return false;
        }

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            String[] data = line.split(",");

            board.currentPlayer = Integer.parseInt(parseData(data[0]));

            board.switch_turn_check = Integer.parseInt(parseData(data[1]));

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static PieceController[][] loadGame(ChessBoard board) {
        File file = new File("game_board_save.txt");
        if (!file.exists()) {
            return null;
        }

        PieceController[][] pieces = new PieceController[6][7];

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");

                int playerIndex = Integer.parseInt(parseData(data[0]));
                PieceType pieceType = PieceType.valueOf(parseData(data[1]));
                int x = Integer.parseInt(parseData(data[2]));
                int y = Integer.parseInt(parseData(data[3]));

                // Use the callback to create PieceController
                pieces[y][x] = PieceFactory.get_piece_controller(pieceType, playerIndex);
                pieces[y][x].set_board(board);
            }

            bufferedReader.close();

            // Set icons for the loaded pieces
            board.setIconsForLoadedPieces(pieces);


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return pieces;
    }

    private static String parseData(String data) {
        return data.split(":")[1];
    }
}
