package com.mcgui.ui;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

import java.util.List;

public class FigureService {
    public  Node createCross(double size){
        double offset = size*0.3;
        double lineWidth = size*0.06;
        Line line1 = new Line(-offset, -offset, offset, offset);
        Line line2 = new Line(-offset, offset, offset, -offset);
        for(var line : List.of(line1, line2)){
            line.setStrokeLineCap(StrokeLineCap.ROUND);
            line.setStrokeWidth(lineWidth);
            line.setStroke(Color.BLACK);
        }
        return new Group(line1, line2);
    }
    public  Node createCircleAndCross(double size){
        double offset = size*0.3;
        double lineWidth = size*0.06;
        Line line1 = new Line(-offset, -offset, offset, offset);
        Line line2 = new Line(-offset, offset, offset, -offset);
        for(var line : List.of(line1, line2)){
            line.setStrokeLineCap(StrokeLineCap.ROUND);
            line.setStrokeWidth(lineWidth);
            line.setStroke(Color.BLACK);
        }
        Circle circle = new Circle(offset);
        circle.setStrokeLineCap(StrokeLineCap.ROUND);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(lineWidth+0.05);
        circle.setFill(Color.WHITE);
        return new Group(circle, line1, line2);
    }

    public  Node createCircle(double size){
        double offset = size*0.35;
        double lineWidth = size*0.06;
        Circle circle = new Circle(offset);
        circle.setStrokeLineCap(StrokeLineCap.ROUND);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(lineWidth);
        circle.setFill(Color.WHITE);
        return circle;
    }

    public  Node createShadedCircle(double size){
        double offset = size*0.35;
        double lineWidth = size*0.06;
        Circle circle = new Circle(offset);
        circle.setStrokeLineCap(StrokeLineCap.ROUND);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(lineWidth);
        return circle;
    }

}
