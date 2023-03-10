using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class pared : MonoBehaviour
{
    void OnTriggerEnter2D (Collider2D col)
	{
		if(col.gameObject.tag == "Ball"){
																		//Increases the score value in the GameManager class by one
			col.gameObject.GetComponent<Ball>().SetDirection(transform.position);		//Accesses the 'Ball' component of the object and calls the 'SetDirection()' function, sending over the brick's position

			Destroy(gameObject);
		}
	}
}
