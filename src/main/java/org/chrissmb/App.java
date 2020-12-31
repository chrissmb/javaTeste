package org.chrissmb;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(4, 10, 3);
        Matrix m = nn.predict(new Double[] {2d, 3d, 4d, 1d});
        System.out.println(m);
    }
}
