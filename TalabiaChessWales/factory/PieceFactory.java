// Class function: 

package TalabiaChessWales.factory;

import TalabiaChessWales.PieceType;
import TalabiaChessWales.controller.*;
import TalabiaChessWales.model.Piece;

public abstract class PieceFactory{

    static Piece get_piece_model(PieceType type)
    {
        Piece ret = new Piece();
        ret.setPieceType(type);
        return ret;
    }


    public static PieceController get_piece_controller(PieceType type, int index) {
        Piece model = get_piece_model(type);
        PieceController controller;
    
        switch (type) {
            case HOURGLASS:
                controller = new HourGlassController(model);
                break;
            case PLUS:
                controller = new PlusController(model);
                break;
            case POINT:
                controller = new PointController(model);
                break;
            case SUN:
                controller = new SunController(model);
                break;
            case TIME:
                controller = new TimeController(model);
                break;
            default:
                // Handle the case where an unsupported piece type is provided.
                throw new IllegalArgumentException("Unsupported piece type: " + type);
        }
    
        // Set the player index
        controller.get_model().setPlayerIndex(index);
        return controller;
    }
    public static PieceController get_hourglass_controller()
    {
        PieceType type = PieceType.HOURGLASS;
        Piece model = get_piece_model(type);
        return (PieceController) new HourGlassController(model);
    }

    public static PieceController get_plus_controller()
    {
        PieceType type = PieceType.PLUS;
        Piece model = get_piece_model(type);
        return (PieceController) new PlusController(model);
    }

    public static PieceController get_point_controller()
    {
        PieceType type = PieceType.POINT;
        Piece model = get_piece_model(type);
        return (PieceController) new PointController(model);
    }

    public static PieceController get_sun_controller()
    {
        PieceType type = PieceType.SUN;
        Piece model = get_piece_model(type);
        return (PieceController) new SunController(model);
    }

    public static PieceController get_time_controller()
    {
        PieceType type = PieceType.TIME;
        Piece model = get_piece_model(type);
        return (PieceController) new TimeController(model);
    }

    public static PieceController get_hourglass_controller(int index)
    {
        PieceController ret = get_hourglass_controller();
        ret.get_model().setPlayerIndex(index);
        return ret;
    }

    public static PieceController get_plus_controller(int index)
    {
        PieceController ret = get_plus_controller();
        ret.get_model().setPlayerIndex(index);
        return ret;
    }

    public static PieceController get_point_controller(int index)
    {
        PieceController ret = get_point_controller();
        ret.get_model().setPlayerIndex(index);
        return ret;
    }

    public static PieceController get_sun_controller(int index)
    {
        PieceController ret = get_sun_controller();
        ret.get_model().setPlayerIndex(index);
        return ret;
    }

    public static PieceController get_time_controller(int index)
    {
        PieceController ret = get_time_controller();
        ret.get_model().setPlayerIndex(index);
        return ret;
    }

    PieceController controller = PieceFactory.get_hourglass_controller();



    
}
