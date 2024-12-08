package com.mycompany.perceptron;

public class Perceptron {

    public static void main(String[] args) {
        // Entradas y resultados esperados
        int[][] inputs = {
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
        };
        int[] expectedOutputs = {1, -1, -1, -1};

        // Inicialización de pesos, tasa de aprendizaje y sesgo
        //double w0 = 0.6473185, w1 = 0.37817776, w2 = 0.33160055; // Pesos iniciales AND
        double w0 = 0.5;
        double w1 = -0.3;
        double w2 = 0.8;

        double learningRate = 0.4;     // Tasa de aprendizaje
        int bias = 1;                  // Sesgo

        boolean errorFound;
        int iteration = 0;
        
        System.out.printf("\nPesos iniciales: w0 = %.8f, w1 = %.8f, w2 = %.8f\n", w0, w1, w2);

        do {
            errorFound = false;
            System.out.println("\nIteración: " + (++iteration));

            for (int i = 0; i < inputs.length; i++) {
                int x1 = inputs[i][0];
                int x2 = inputs[i][1];
                int expected = expectedOutputs[i];

                // Cálculo de la salida del perceptrón
                double y = w0 * bias + w1 * x1 + w2 * x2;
                int yD = y >= 0 ? 1 : -1; // Clasificación basada en la salida

                // Cálculo del error
                int error = expected - yD;

                System.out.printf("Fila %d: x1 = %d, x2 = %d, y = %.2f, yD = %d, esperado = %d, error = %d\n",
                        i + 1, x1, x2, y, yD, expected, error);

                if (error != 0) {
                    // Actualización de pesos
                    w0 = w0 + (learningRate * error * bias);
                    w1 = w1 + (learningRate * error * x1);
                    w2 = w2 + (learningRate * error * x2);

                    System.out.printf("\tActualizando pesos: w0 = %.8f, w1 = %.8f, w2 = %.8f\n", w0, w1, w2);
                    errorFound = true;
                    break; // Termina el cálculo de las demás filas en esta iteración
                }
            }
        } while (errorFound); // Continúa hasta que no haya errores

        System.out.println("\nEntrenamiento completado.");
        System.out.printf("Pesos finales: w0 = %.8f, w1 = %.8f, w2 = %.8f\n", w0, w1, w2);
    }
}
