/**********************************************************************************
 * Copyright (c) 2015 Xoidre-Team <me@joaquimley.com>                             *
 *                                                                                *
 * Permission is hereby granted, free of charge, to any person obtaining a copy   *
 * of this software and associated documentation files (the "Software"), to deal  *
 * in the Software without restriction, including without limitation the rights   *
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell      *
 * copies of the Software, and to permit persons to whom the Software is          *
 * furnished to do so, subject to the following conditions:                       *
 *                                                                                *
 * The above copyright notice and this permission notice shall be included in all *
 * copies or substantial portions of the Software.                                *
 *                                                                                *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR     *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,       *
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE    *
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER         *
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,  *
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  *
 * SOFTWARE.                                                                      *
 **********************************************************************************/

package com.xoidre.validarportugal.helper;

public final class DocumentDetail {

    private static final String TAG = DocumentDetail.class.getSimpleName();

    private DocumentDetail() {
    }

    /**
     * TODO to implement
     * Tanto o NIF como NIPC são constituídos por 9 digítos, sendo que o primeiro tem significados diferentes:
     * <p/>
     * 1 ou 2 (pessoa singular)
     * 5 (pessoa colectiva)
     * 6 (pessoa colectiva pública)
     * 8 (empresário em nome individual)
     * 9 (pessoa colectiva irregular ou número provisório)
     * <p/>
     * 1 a 3: Pessoa singular, o 3 ainda não está atribuido;2
     * 45: Pessoa singular. Os algarismos iniciais "45" correspondem aos cidadãos não residentes que apenas obtenham em território português rendimentos sujeitos a retenção na fonte a título definitivo.2
     * 5: pessoa coletiva obrigada a registo no Registo Nacional de Pessoas Coletivas;3
     * 6: Organismo da Administração Pública Central, Regional ou Local;
     * 70: Herança Indivisa, em que o autor da sucessão não era empresário individual, ou Herança Indivisa em que o cônjuge sobrevivo tem rendimentos comerciais;
     * 71: Não residentes coletivos sujeitos a retenção na fonte a título definitivo.
     * 72: Fundos de investimento.
     * 77: Atribuição Oficiosa de NIF de sujeito passivo (entidades que não requerem NIF junto do RNPC).
     * 79: Regime excecional - Expo 98.
     * 8: "empresário em nome individual" (deixou de ser utilizado, já não é válido);
     * 90 e 91: Condomínios, Sociedade Irregulares, Heranças Indivisas cujo autor da sucessão era empresário individual.
     * 98: Não residentes sem estabelecimento estável.
     * 99: Sociedades civis sem personalidade jurídica.
     *
     * @param number
     * @return
     */
    private String getNifError(String number) {
        return null;
    }

    /**
     * TODO to implement
     * O NISS de Pessoa Singular (PS) começa por 1. O NISS de Pessoa Colectiva
     * (PC) começa por 2.
     *
     * @param number
     * @return
     */
    private String getNissDetails(String number) {
        return null;
    }
}
