package com.mycompany.laboratorio3;

import java.util.Random;
import javax.swing.JButton;

public abstract class Numeros implements Cuadricula{
    
    public JButton[][] botones;
    
    public Numeros() {
        botones = new JButton[9][9];
    }
    
    public void colocar() {
        Random random = new Random();
        boolean[][][] used = new boolean[9][9][10];

        int filledCells = 0;
        while (filledCells < 12) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            int num = random.nextInt(9) + 1;

            if (botones[row][col].getText().isEmpty() && !used[row][col][num] && !buscarNum(row, col, num) 
                    && !numero3x3Inicial(row, col, num)) {
                botones[row][col].setText(Integer.toString(num));
                botones[row][col].setEnabled(false);
                for (int j = 0; j < 9; j++) {
                    used[row][j][num] = true;
                    used[j][col][num] = true;
                }
                filledCells++;
            }
        }
    }
    
    @Override
    public boolean numero3x3Inicial(int row, int col, int num) {
        int subcuadriculaRow = row / 3 * 3;
        int subcuadriculaCol = col / 3 * 3;

        String numStr = Integer.toString(num);
        for (int i = subcuadriculaRow; i < subcuadriculaRow + 3; i++) {
            for (int j = subcuadriculaCol; j < subcuadriculaCol + 3; j++) {
                if (i != row && j != col && botones[i][j].getText().equals(numStr)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public abstract boolean buscar(int row, int col);
    public abstract boolean buscarNum(int row, int col, int num);
}
