package s2it.java.test.exercise9;

import java.util.Optional;
import java.util.function.Function;

/**
 * Considerando a estrutura de uma árvore binária:
 *     public class BinaryTree {
 *         int valor;
 *         BinaryTree left;
 *         BinaryTree right;
 *     }
 *     
 *     Desenvolva um método que dado um nó da árvore calcule a soma de todos os nós
 *     subsequentes.
 */
public class Excercise9 {
	
	/**
	 * Funcao pura que realiza a soma dos valores de uma arvore binaria
	 * a partir de um determinado nó passado por parametro.
	 * @param node Nó de inicio da busca para a soma dos nós subsequentes.
	 * @return A soma dos nós a partir do nó passado.
	 */
	private static int sum(final BinaryTree node) {
		final Function<BinaryTree, Integer> sumNodes = n -> n.valor + sum(n.right) + sum(n.left);

		return Optional.ofNullable(node)
				.map(sumNodes)
				.orElse(0);
	}
	
	/**
	 * Main
	 * 
	 * @param args Ignorado!
	 */
	public static void main(String[] args) {
		final BinaryTree root = new BinaryTree();

		final BinaryTree rootRight = new BinaryTree();
		final BinaryTree rootLeft = new BinaryTree();

		final BinaryTree rootRightLeft = new BinaryTree();
		final BinaryTree rootRightRight = new BinaryTree();

		final BinaryTree rootLeftRight = new BinaryTree();
		final BinaryTree rootLeftLeft = new BinaryTree();

		final BinaryTree unbalanced = new BinaryTree();

		root.valor = 1;

		rootRight.valor = 1;
		rootLeft.valor = 1;

		rootLeftRight.valor = 1;
		rootLeftLeft.valor = 1;

		rootRightRight.valor = 1;
		rootRightLeft.valor = 10;
		
		unbalanced.valor = 99;

		root.right = rootRight;
		root.left = rootLeft;
		
		rootRight.right = rootRightRight; rootRight.left = rootRightLeft;

		rootLeft.right = rootLeftRight;
		rootLeft.left = rootLeftLeft;
		
		rootLeftLeft.right = unbalanced;
		
		int sum = sum(root);
		
		System.out.println("############# RESULTADO ############# ");
		System.out.println(sum);
		System.out.println("");
		
		unbalanced.valor = 100;
		
		int sum2 = sum(root);
		
		System.out.println("############# RESULTADO ############# ");
		System.out.println(sum2);
		System.out.println("");
	}

}
