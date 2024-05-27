package model;

import enums.WeatherCondition;
import exceptions.MuerteException;
import exceptions.PokemonException;
import exceptions.RoundStartException;
import interfaces.Atacable;

public class PokemonAgua extends Pokemon{

    private int valorHidratacion;
    private double precisionLluvia;

    public PokemonAgua(String nombre, int salud, int ataque, int defensa, int valorHidratacion, double precisionLluvia) throws PokemonException {
        super(nombre, salud, ataque, defensa);
        this.valorHidratacion = valorHidratacion;
        this.precisionLluvia = precisionLluvia;
    }

    @Override
    public void setSalud(int salud) {
        if(salud > SALUD_MAX){
            salud = SALUD_MAX;
        }
        super.setSalud(salud);
    }

    public int getValorHidratacion() {
        return valorHidratacion;
    }

    public double getPrecisionLluvia() {
        return precisionLluvia;
    }

    @Override
    public void atacar(Atacable atacable, WeatherCondition weatherCondition) throws MuerteException {

    }

    @Override
    public void roundStart(WeatherCondition weatherCondition) throws RoundStartException {

    }
}
