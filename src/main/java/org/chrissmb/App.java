package org.chrissmb;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        
        final double[] R = {0, 0};
        final double[] G = {0, 1};
        final double[] B = {1, 0};
        
        double[][] inputs = {
            {255, 0, 0},
            {0, 255, 0},
            {0, 0, 255},
        };
        double[][] targets = {R, G, B};
        
        NeuralNetwork nn = new NeuralNetwork(inputs[0].length, 10, targets[0].length);
        nn.fit(inputs, targets, 99999);

        double[][] resolveIt = {
            {250, 13, 5}, //0,0
            {12, 33, 253}, //1,0
            {2, 239, 5}, //0,1
            {50, 10, 255}, //1,0
        };
        
        System.out.println("result:");
        for (double input[] : resolveIt) {
            Matrix output = nn.predict(input);
            System.out.println(output);
        }
    }
}
