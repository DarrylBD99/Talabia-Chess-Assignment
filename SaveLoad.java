import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveLoad {
    public static void saveGame(PieceController[][] pieces) {
        try {
            FileWriter writer = new FileWriter("game_save.txt");

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

                    writer.write(String.join(",", data) + "\n");
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

        PieceController[][] pieces = new PieceController[7][6]; // Assuming an 8x8 board

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
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pieces;
    }

    private static String parseData(String data) {
        return data.split(":")[1];
    }
}
