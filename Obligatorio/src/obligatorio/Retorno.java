/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio;

/**
 *
 * @author alumnoFI
 */
public class Retorno {
    

        enum Resultado {
		OK, ERROR_1, ERROR_2, ERROR_3, ERROR_4, ERROR_5, NO_IMPLEMENTADA
	};

	int valorEntero;
	String valorString;
	Resultado resultado;

    public Retorno(Resultado resultado) {
        this.resultado = resultado;
    }

    

}
