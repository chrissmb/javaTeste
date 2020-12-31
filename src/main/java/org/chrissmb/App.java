package org.chrissmb;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(2, 10, 1);
        
        double[][] inputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        double[][] targets = {{0}, {1}, {1}, {0}};
        nn.fit(inputs, targets, 50000);

        for (double input[] : inputs) {
            Matrix output = nn.predict(input);
            System.out.println(output);
        }
    }
}
