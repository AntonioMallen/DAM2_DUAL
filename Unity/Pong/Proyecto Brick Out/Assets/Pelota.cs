using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Pelota : MonoBehaviour
{


    public float speed = 30;
    public GameObject pelota;
    //public Text textoL;
    //public Text textoR;
    public int scoreL = 0;
    public int scoreR = 0;

    void Start()
    {
        // Initial Velocity
        GetComponent<Rigidbody2D>().velocity = Vector2.down * speed;
    }


    void OnCollisionEnter2D(Collision2D col)
    {

        if (col.gameObject.name == "Paleta")
        {
            // Calculate hit Factor
            float y = hitFactor(transform.position,
                                col.transform.position,
                                col.collider.bounds.size.x);

            // Calculate direction, make length=1 via .normalized
            Vector2 dir = new Vector2(y,1).normalized;

            // Set Velocity with dir * speed
            GetComponent<Rigidbody2D>().velocity = dir * speed;
        }
        if (col.gameObject.name == "ParedAbajo")
        {
            Debug.Log("Perdiste");
            Destroy(pelota);

        }


    }



    float hitFactor(Vector2 ballPos, Vector2 racketPos,
                float racketHeight)
    {
        // ascii art:
        // ||  1 <- at the top of the racket
        // ||
        // ||  0 <- at the middle of the racket
        // ||
        // || -1 <- at the bottom of the racket
        return (ballPos.x - racketPos.x) / racketHeight;

    }
}