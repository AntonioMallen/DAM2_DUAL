using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class clickJugar : MonoBehaviour
{
    public string nombre;
    // Start is called before the first frame update
    public void clickJugarMetodo(string nombre){
        SceneManager.LoadScene(nombre);
    }
}



