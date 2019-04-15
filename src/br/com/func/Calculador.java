package br.com.func;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculador {

    public Number calcularVolumeMaximoCaixa(Number comprimento, Number largura) {
        Float vMax = null;
        Double c = comprimento.doubleValue();
        Double l = largura.doubleValue();

        Double xc = -4 * c;
        Double xl = -4 * l;
        Double cl = c * l;

        Double a = new Double(12);
        Double b = xl + xc;
        Double cc = cl;


        Double delta = delta(a, b, cc);
        Float bascaraX1 = bascaraXUmaLinha(a, b, delta);
        Float bascaraX2 = bascaraXDuasLinhas(a, b, delta);

        if (bascaraX1.floatValue() > 0 && bascaraX1.floatValue() < (largura.floatValue() / 2)) {
            vMax = vMax(bascaraX1, c , l);
        } else if (bascaraX2.floatValue() > 0 && bascaraX2.floatValue() < (largura.floatValue() / 2)) {
            vMax = vMax(bascaraX2, c, l);
        }

        return vMax;
    }

    private Float vMax(Float x, Double comprimento, Double largura) {
        Float x2 = x * 2;

        Double c2x = comprimento - x2;
        Double l2x = largura - x2;

        Double c2x_l2x = c2x * l2x;
        Number fx = x * c2x_l2x;
        return arredondarComEscala(fx, 2);
    }

    private Double delta(Double a, Double b, Double c) {
        Double bQuadrado = Math.pow(b, 2);
        Double delta = bQuadrado - 4 * (a * c);

        return delta;

    }

    private Float bascaraXUmaLinha(Double a, Double b, Double delta) {
        BigDecimal raizDeDelta = new BigDecimal(Math.sqrt(delta)).setScale(2, RoundingMode.FLOOR);
        b = b * (-1);
        Number baskarah = new BigDecimal(b).add(raizDeDelta).divide(new BigDecimal(2).multiply(new BigDecimal(a))).setScale(2, RoundingMode.FLOOR);
        return baskarah.floatValue();
    }

    private Float bascaraXDuasLinhas(Double a, Double b, Double delta) {
        BigDecimal raizDeDelta = new BigDecimal(Math.sqrt(delta)).setScale(2, RoundingMode.FLOOR);
        b = b * (-1);
        BigDecimal divdendo = new BigDecimal(2).multiply(new BigDecimal(a));
        Number baskarah = (b - raizDeDelta.floatValue()) / ( 2 * a);

        return new BigDecimal(baskarah.floatValue()).setScale(2, RoundingMode.HALF_EVEN).floatValue();
    }

    private Float arredondarComEscala(Number numero, Integer escala) {
        return new BigDecimal(numero.floatValue()).setScale(escala, RoundingMode.HALF_EVEN).floatValue();
    }


}
