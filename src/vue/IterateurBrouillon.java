package vue;

import javafx.geometry.Point2D;

import java.util.Iterator;

public class IterateurBrouillon implements Iterator<Point2D> {

    private Iterator<Point2D> it_point;

    IterateurBrouillon(Iterator<Point2D> iterator){
        this.it_point = iterator;
    }

    @Override
    public boolean hasNext(){

        if (it_point.hasNext()){
            it_point.next();
            return it_point.hasNext();
        }
        else {
            return false;
        }

    }

    @Override
    public Point2D next() {
        return null;
    }
}
