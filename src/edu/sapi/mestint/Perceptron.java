package edu.sapi.mestint;


import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

public class Perceptron {

    public static void main(String[] args) {
        Perceptron perceptron = new Perceptron();
        if (args.length > 0) {
            perceptron.learnLetters();
        } else {
            perceptron.learnANDFunction();
        }
    }

    private void learnLetters() {
        int[] letterH = {1, 0, 1, 1, 1, 1, 1, 0, 1};
        int[] letterI = {0, 1, 0, 0, 1, 0, 0, 1, 0};
        int[][] toTrain = {
                {1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 0, 1, 0, 0, 1, 0}
        };
        int[][] labels = {
                {1, 0},
                {0, 1}
        };
        double[][] weights = {generateRandomDoubleList(letterH.length + 1), generateRandomDoubleList(letterI.length + 1)};
        double learningRate = 0.1;
        double error = 1;
        while (error != 0) {
            error = 0;
            for (int i = 0; i < toTrain.length; i++) {
                int yi1 = hardlim(multiplyValuesAndSum(toTrain[i], weights[0]));
                int yi2 = hardlim(multiplyValuesAndSum(toTrain[i], weights[1]));

                int ei1 = labels[i][0] - yi1;
                int ei2 = labels[i][1] - yi2;

                weights[0] = sumArrays(sum(toTrain[i], learningRate * ei1 * toTrain[i][0]), weights[0]);
                weights[1] = sumArrays(sum(toTrain[i], learningRate * ei2 * toTrain[i][0]), weights[1]);
                printWeights(weights);
                error += Math.pow(ei1, 2) + Math.pow(ei2, 2);
            }
        }

    }

    private void printWeights(double[][] weights) {
        System.out.println();
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                System.out.print(weights[i][j] + ", ");
            }
            System.out.println();
        }
    }

    private double multiplyValuesAndSum(int[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    private double[] sumArrays(double[] a, double[] b) {
        for (int i = 0; i < a.length; i++) {
            b[i] += a[i];
        }
        return b;
    }

    private double[] sum(int[] a, double val) {
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] += val;
        }
        return result;
    }

    private void learnANDFunction() {
        PerceptronWindow window = new PerceptronWindow("AND Function");
        window.drawLine(0, 350, 400, 350, Color.BLACK);
        window.drawLine(50, 0, 50, 400, Color.BLACK);
        int[][] dataIn = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] result = {0, 0, 0, 1};
        double[] wight = generateRandomDoubleList(3);
        double error = 1;
        while (error > 0.001) {
            error = 0;
            int j = 0;
            for (int i = 0; i < dataIn.length; i++) {
                int lr = result[j++] - hardlim(dataIn[i][0] * wight[0] + dataIn[i][1] * wight[1] + wight[2]);
                wight[0] += lr * dataIn[i][0];
                wight[1] += lr * dataIn[i][1];
                wight[2] += lr;
                window.drawLine(50, 350, 400, (int) (wight[2] * 50), Color.BLUE);
                error += Math.abs(lr);
            }
        }
        window.drawLine(50, 350, 400, (int) (wight[2] * 50), Color.GREEN);
    }

    private double[] generateRandomDoubleList(int size) {
        return DoubleStream
                .generate(ThreadLocalRandom.current()::nextGaussian)
                .limit(size)
                .toArray();
    }

    private int hardlim(double value) {
        return value < 0 ? 0 : 1;
    }
}
