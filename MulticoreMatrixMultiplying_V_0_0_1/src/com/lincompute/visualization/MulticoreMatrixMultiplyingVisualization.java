package com.lincompute.visualization;

import com.lincompute.implementation.Matrix;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MulticoreMatrixMultiplyingVisualization extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Matrix Multiplication Performance Comparison");
        
        // Create axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Matrix Size");
        yAxis.setLabel("Time (milliseconds)");
        
        // Create chart
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Classical Matrix Multiplying vs Multicore Blocs Matrix Multiplying");
        lineChart.setCreateSymbols(true);
        
        // Create series
        XYChart.Series<Number, Number> classicalSeries = new XYChart.Series<>();
        classicalSeries.setName("Classical Method");
        XYChart.Series<Number, Number> parallelSeries = new XYChart.Series<>();
        parallelSeries.setName("Parallel Method (4 threads)");
        
        // Run performance tests
        runPerformanceTests(classicalSeries, parallelSeries);
        
        lineChart.getData().addAll(classicalSeries, parallelSeries);
        
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(
            new Label("Performance Comparison: Classical Matrix Multiplying vs Multicore Bloc Matrix Multiplying"),
            lineChart
        );
        
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void runPerformanceTests(XYChart.Series<Number, Number> classicalSeries, 
                                   XYChart.Series<Number, Number> parallelSeries) {
        int[] sizes = {50, 100, 150, 200, 250, 300, 350, 400};
        
        for (int size : sizes) {
            System.out.println("Testing matrix size: " + size);
            
            // Create test matrices
            Matrix A = createRandomMatrix(size, size);
            Matrix B = createRandomMatrix(size, size);
            
            // Warm up JVM
            Matrix.multiplyingMatrix(A, B);
            Matrix.multiplyMatricesParallel(A, B);
            
            // Test classical method multiple times for average
            long classicalTime = 0;
            for (int i = 0; i < 3; i++) {
                classicalTime += measureTime(() -> Matrix.multiplyingMatrix(A, B));
            }
            classicalTime /= 3;
            
            // Test parallel method multiple times for average
            long parallelTime = 0;
            for (int i = 0; i < 3; i++) {
                parallelTime += measureTime(() -> Matrix.multiplyMatricesParallel(A, B));
            }
            parallelTime /= 3;
            
            classicalSeries.getData().add(new XYChart.Data<>(size, classicalTime));
            parallelSeries.getData().add(new XYChart.Data<>(size, parallelTime));
            
            System.out.printf("Size: %d, Classical Matrix Multiplying, Multicore Bloc Matrix Multiplying: %d ms, : %d ms, Speedup: %.2fx%n", 
                            size, classicalTime, parallelTime, (double)classicalTime/parallelTime);
        }
    }
    
    private long measureTime(Runnable task) {
        long startTime = System.nanoTime();
        task.run();
        return (System.nanoTime() - startTime) / 1000000;
    }
    
    private Matrix createRandomMatrix(int rows, int cols) {
        Double[][] data = new Double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = Math.random() * 100;
            }
        }
        return new Matrix(data);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}