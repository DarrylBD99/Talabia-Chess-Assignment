import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveLoad {
    public static void saveGame(PieceController[][] pieces) {
        try {
            FileWriter writer = new FileWriter("game_save.txt");
            // writer.write("index,type,x,y\n");

            for (int y = 0; y < pieces.length; y++) {
                PieceController[] pieceRow = pieces[y];
                for (int x = 0; x < pieceRow.length; x++) {
                    Piece piece = pieceRow[x].get_model();

                    String[] data = {
                        "index:" + piece.getPlayerIndex(),
                        "type:" + piece.getPieceType().name(),
                        "x:" + x, 
                        "y:" + y
                    };

                    writer.write(String.join(",", data)  + "\n");
                }
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PieceController[][] loadGame() {
        File file = new File("game_save.txt");
        if (!file.exists()) {
            return null;    
        }
    
        PieceController[][] pieces = new PieceController[8][8]; // Assuming an 8x8 board
    
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
    
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                int playerIndex = Integer.parseInt(data[0].split(":")[1]);
                PieceType pieceType = PieceType.valueOf(data[1].split(":")[1]);
                int x = Integer.parseInt(data[2].split(":")[1]);
                int y = Integer.parseInt(data[3].split(":")[1]);
    
                // Create and deserialize the piece using playerIndex and pieceType
                Piece model = new Piece();
                model.setPlayerIndex(playerIndex);

                // Create a PieceController and associate it with the model and view.
                //pieces[y][x] = PieceController.get_piece_controller(model);
            }
    
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return pieces;
    }
}