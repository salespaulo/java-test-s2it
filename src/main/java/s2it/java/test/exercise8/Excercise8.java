package s2it.java.test.exercise8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 8 - Dados dois numeros inteiros A e B, crie um terceiro inteiro C seguindo as seguintes regras:
 *   - O primeiro número de C é o primeiro número de A;
 *   - O segundo número de C é o primeiro número de B;
 *   - O terceiro número de C é o segundo número de A;
 *   - O quarto número de C é o segundo número de B;
 *   
 *     Assim sucessivamente...
 *     
 *   - Caso os números de A ou B sejam de tamanhos diferentes, completar C com o restante 
 *     dos números do inteiro maior. Ex: A = 10256, B = 512, C deve ser 15012256.
 *   - Caso C seja maior que 1.000.000, retornar -1
 *   
 *     Desenvolva um algoritmo que atenda a todos os requisitos acima. 
 */
public final class Excercise8 {
	
	/**
	 * Funcao pura que somente transforma um Integer (int) criando 
	 * uma List<Integer> com cada algarismos do Integer (int) original.
	 * <p>
	 * <b>
	 * 		intToIntList: ( Integer => List<Integer> )
	 * </b>
	 * </p>
	 */
	private static final Function<Integer, List<Integer>> intToIntList = 
			value -> Integer.toString(value)
				.chars()
				.map(Character::getNumericValue)
				.mapToObj(Integer::valueOf)
				.collect(Collectors.toList());
	
	/**
	 * Funcao pura que colapsa o Integer em cada algarismo compondo ele.
	 * @param value Inteiro original
	 * @return Lista de inteiros que sao os algarismos do inteiro original.
	 */
	private static final List<Integer> collapseInteger(final Integer value) {
		return Optional.ofNullable(value)
					.map(intToIntList)
					.orElse(Collections.emptyList());
	}
	
	/**
	 * Funcao pura que cria uma terceira lista intercalando os elementos
	 * encontrados nas listas passadas por parametro.
	 * @param l1 Lista um
	 * @param l2 Lista dois
	 * @return Lista com os elementos intercalados das duas listas passadas.
	 */
	private static List<Integer> intercaleIntegers(final List<Integer> l1, final List<Integer> l2) {
        final Iterator<Integer> i1 = l1.iterator();
        final Iterator<Integer> i2 = l2.iterator();
        final List<Integer> l3 = new ArrayList<>();
        
        while (i1.hasNext() || i2.hasNext()) {
        	if (i1.hasNext()) {
				Optional.ofNullable(i1.next())
					.ifPresent(l3::add);
        	}
        	
        	if (i2.hasNext()) {
				Optional.ofNullable(i2.next())
					.ifPresent(l3::add);
        	}
        }
        
        return l3.size() < Integer.MAX_VALUE ? l3 : Collections.emptyList();
	}

	/**
	 * Funcao pura que representa um wrapper para a funcao {@link Excercise8#intercaleIntegers(List, List)}.
	 * @param valueA Inteiro original do valor A.
	 * @param valueB Inteiro original do valor B.
	 * @return Lista com os algarismos intercalados dos dois inteiros passados.
	 */
	private static List<Integer> gerar(int valueA, int valueB) {
		final List<Integer> listaA = collapseInteger(valueA);
		final List<Integer> listaB = collapseInteger(valueB);

		return intercaleIntegers(listaA, listaB);
	}

	/**
	 * Funcao para recuperar o valor passado como argumento na posicao passada por
	 * parametro ou retorna um valor randomico inteiro.
	 */
	private static int getValueAtPosOrRandomInt(final String[] arguments, int position) {
		final Predicate<String[]> hasPos = args -> args.length >= position;
		final Function<String[], String> getPos = args -> args[position - 1];

		return Optional.ofNullable(arguments)
				.filter(hasPos)
				.map(getPos)
				.map(Integer::valueOf)
				.orElse(new Random()
						.nextInt(Integer.MAX_VALUE));
	}
	
	/**
	 * Main
	 * 
	 * @param args Pode ser passado os valores valorA e/ou valorB inteiros para teste, senao sera 
	 * assumido um valor randomico para realizar os testes.
	 */
	public static void main(final String... args) {
		int valueA = getValueAtPosOrRandomInt(args, 1);
		int valueB = getValueAtPosOrRandomInt(args, 2);
		
        final List<Integer> result = gerar(valueA, valueB);

		System.out.println("#############  VALOR A  ############# ");
		System.out.println(valueA);
		System.out.println("");


		System.out.println("#############  VALOR B  ############# ");
		System.out.println(valueB);
		System.out.println("");

		System.out.println("############# RESULTADO ############# ");
		System.out.println(result.isEmpty() ? -1 : result);
		System.out.println("");
        
		System.out.println("######### SOMA DO RESULTADO ######### ");

		System.out.println(result.stream()
				.mapToInt(Integer::valueOf)
				.sum());

		System.out.println("");
    }
	
}
