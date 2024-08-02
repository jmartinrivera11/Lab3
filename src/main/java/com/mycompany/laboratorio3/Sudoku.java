
package com.mycompany.laboratorio3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sudoku extends Numeros implements ActionListener, Colores {
    
    JFrame frame;
    
    public Sudoku() {
        frame = new JFrame("SUDOKU");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JPanel panelSudoku = new JPanel();
        panelSudoku.setLayout(new GridLayout(3, 3));
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JPanel panel = new JPanel(new GridLayout(3, 3));
                
                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        JButton boton = new JButton();
                        boton.addActionListener(this);
                        Dimension tamaño = new Dimension(80, 60);
                        boton.setPreferredSize(tamaño);
                        botones[i * 3 + a][j * 3 + b] = boton;
                        panel.add(boton);
                    }
                }
                panelSudoku.add(panel);
            }
        }
        frame.add(panelSudoku);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        colocar();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickear = (JButton) e.getSource();
        int row = -1;
        int col = -1;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (botones[i][j] == clickear) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }
        
        String siguiente = "";
        boolean valido = false;

        while (!valido) {
            try {
                siguiente = JOptionPane.showInputDialog("Ingreser numero de 1 al 9");
                if (siguiente != null) {
                    int ingresado = Integer.parseInt(siguiente);
                    if (ingresado >= 1 && ingresado <= 9) {
                        valido = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Ingresar numero valido [1 - 9]");
                    }
                } else {
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingresar numero valido");
            }
        }

        botones[row][col].setText(siguiente);
        colores();

        if (finJuego()) {
            JOptionPane.showMessageDialog(null, "GANASTE!!!");
            System.exit(0);
        }
    }
    
    @Override
    public boolean buscar(int row, int col) {
        String numBoton = botones[row][col].getText();
        for (int i = 0; i < 9; i++) {
            if (botones[row][i].getText().equals(numBoton) && i != col) {
                return true;
            }
            if (botones[i][col].getText().equals(numBoton) && i != row) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void colores() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!botones[i][j].getText().isEmpty()) {
                    if (buscar(i, j) || numero3x3(i, j)) {
                        botones[i][j].setBackground(Color.RED);
                    } else {
                        botones[i][j].setBackground(Color.WHITE);
                    }
                } else {
                    botones[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
    
    @Override
    public boolean buscarNum(int row, int col, int num) {
        String numStr = Integer.toString(num);
        for (int i = 0; i < 9; i++) {
            if (botones[row][i].getText().equals(numStr) && i != col) {
                return true;
            }
            if (botones[i][col].getText().equals(numStr) && i != row) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean numero3x3(int row, int col) {
        int subcuadriculaRow = row / 3 * 3;
        int subcuadriculaCol = col / 3 * 3;

        String numBoton = botones[row][col].getText();
        for (int i = subcuadriculaRow; i < subcuadriculaRow + 3; i++) {
            for (int j = subcuadriculaCol; j < subcuadriculaCol + 3; j++) {
                if (i != row && j != col && botones[i][j].getText().equals(numBoton)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean finJuego() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (botones[i][j].getText().isEmpty() 
                        || botones[i][j].getBackground().equals(Color.RED)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
    }
}
