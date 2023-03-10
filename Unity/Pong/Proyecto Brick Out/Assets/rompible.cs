using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class rompible : MonoBehaviour
{
	void OnTriggerEnter2D(Collider2D col)
	{
		if (col.gameObject.tag == "Ball")
		{                                               //Is the tag of the colliding object 'Ball'
			col.gameObject.GetComponent(paredRompible);
			Destroy(gameObject);                                                        //Destroy's the brick
		}
	}
}
/*
 * 
 * 
 * 
 * void OnTriggerEnter2D (Collider2D col)
	{
		if(col.gameObject.tag == "Ball"){												//Is the tag of the colliding object 'Ball'
			manager.score++;															//Increases the score value in the GameManager class by one
			col.gameObject.GetComponent<Ball>().SetDirection(transform.position);		//Accesses the 'Ball' component of the object and calls the 'SetDirection()' function, sending over the brick's position
			manager.bricks.Remove(gameObject);											//Removes this brick from the 'bricks' list in the GameManager

			if(manager.bricks.Count == 0)												//Has the 'bricks' list got no more bricks in it?
				manager.WinGame();														//Call the 'WinGame()' function in the GameManager

			Destroy(gameObject);														//Destroy's the brick
		}
	}*/
