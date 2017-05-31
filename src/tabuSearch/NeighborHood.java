package tabuSearch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * This class provides an implementation for NeighboorHood class
 * @author Florian
 *
 */
public class NeighborHood {
	private int[] solution;
	private ArrayList<Integer[]> domains;
	private List<Move> moves;

	/**
	 * Constructor of NeighborHood
	 * @param solution The solution from which a set of adjacent solutions can be reached
	 * @param domains2 The domain of the current solution
	 */
	public NeighborHood(int[] solution, ArrayList<Integer[]> domains2){
		this.solution = solution;
		this.domains = domains2;
		this.moves = new LinkedList<Move>();
	}

	/**
	 * Determine a new NeighborHood by adding moves
	 * Determina un nuevo vecindario para añadir movimientos
	 */
	public void determineNeighboor(){
		for (int i=0; i<this.solution.length; i++){
			Integer [] values = this.domains.get(i);
			for (int j=0; j<values.length;j++){
				int valueElem = values[j];

				//We keep only values which are different to the solution 
				//mantenemos solo los valores que son diferentes a los de la solucion
				if(valueElem != this.solution[i]){
					Move move = new Move(i, valueElem);
					this.moves.add(move);
				}
			}
		}
	}

	/**
	 * This method allow reducing the current neighborhood, removing elements which are also contained in our TabuList
	 * reduce el actual vecindario, eliminando elementos que se encuentran contenidos en la lista tabu
	 * @param tabuList TabuList which contains wrong/forbidden moves
	 */
	public void reduceNeighborHood(TabuList tabuList){
		Iterator<Move> it = moves.iterator();

		while(it.hasNext())
		{
			Move moveElem = it.next();
			if(tabuList.isTabuElem(moveElem))
			{
				it.remove();
			}
		}
	}
	//demora en este bucle
	/**
	 * This method returns a triple which contains the best neighborhood, the best cost and the best move found.
	 * This solution will be used in the tabu search 
	 * retorna una tripleta que contiene el mejor vecindario, el mejor coste y el mejor movimiento encontrado
	 * @return result A Result which contains the best neighborhood, the best cost and the best move found
	 */
	public BestCandidate getBestPossibleSolution(){
		BestCandidate result = new BestCandidate();

		int bestSolutionCostFound = Integer.MAX_VALUE;
		int[] bestSolutionFound = null;
		Move bestMoveFound = null;
		for(Move movimiento : moves){
			int [] solutionFound = new int[solution.length];
			System.arraycopy(solution, 0, solutionFound, 0, solution.length);

			solutionFound[movimiento.getVariable()] = movimiento.getValue();
			int currentCost = Tools.fitness(solutionFound);
			
			if( currentCost < bestSolutionCostFound ){
				bestSolutionFound = solutionFound;
				bestSolutionCostFound = currentCost;
				bestMoveFound = movimiento;
				
			}
		}
		//System.out.println(moves.size());
		result.setSolution(bestSolutionFound);
		result.setCost(bestSolutionCostFound);
		result.setMove(bestMoveFound);

		//System.out.println(result.toString());

		return result;
	}

}
