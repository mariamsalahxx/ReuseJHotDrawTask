package CH.ifa.draw.contrib;

import CH.ifa.draw.framework.DrawingEditor;
import CH.ifa.draw.framework.DrawingView;
import CH.ifa.draw.framework.Figure;
import CH.ifa.draw.framework.FigureEnumeration;
import CH.ifa.draw.standard.AbstractTool;
import CH.ifa.draw.standard.FigureEnumerator;

import java.util.Vector;

public class MovementTool extends AbstractTool {

    private DrawingEditor editor;

    public MovementTool (DrawingEditor editor){
        super(editor);
        this.editor = editor;
    }
    public void activate() {
        super.activate();
        moveSelectedFigures();
    }

  private void moveSelectedFigures() {
//        DrawingEditor editor = super.editor();
//        if (editor != null) {
//            int inchToPixels = 72;
//            DrawingView view = editor.view();
//            if (view != null) {
//                // Get the selected figures
//                FigureEnumeration selectedFigures = new FigureEnumerator(view().selection());
//                while(selectedFigures.hasMoreElements()){
//                    Figure figure = selectedFigures.nextFigure();
//                    figure.moveBy(2 * inchToPixels, 2 * inchToPixels);
//                }
//            }
//        }
}


}
