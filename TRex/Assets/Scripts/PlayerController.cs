using UnityEngine;

public class PlayerController : MonoBehaviour
{
   
    private Animator animator; 
    
    private CharacterController character;

    
    private Vector3 direction;

    [Space(10)]
     
    public float gravity = 9.81f * 2f;
    public float jumpForce = 8f;

   
    private void Awake()
    {
        
        animator = GetComponent<Animator>();
        character = GetComponent<CharacterController>();
    }

    
    private void OnEnable()
    {
        
        direction = Vector3.zero;
    }

   
    private void Update()
    {
        
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            QuitGame();
            return;   
        }

        
        direction += Vector3.down * gravity * Time.deltaTime;

         
        if (!GameManager.Instance.isGameOver)
        {
            
            if (character.isGrounded)
            {
                
                direction = Vector3.down;
                animator.SetBool("isGrounded", true);

              
                if (Input.GetKeyDown(KeyCode.Space))
                {
                     
                    direction = Vector3.up * jumpForce;
                    animator.SetBool("isGrounded", false);
                }
            }
        }

        
        if (GameManager.Instance.isGameOver)
        {
            if (Input.GetKeyDown(KeyCode.R))
            {
                
                GameManager.Instance.NewGame();
            }
        }

         
        character.Move(direction * Time.deltaTime);
    }

 
    private void OnTriggerEnter(Collider other)
    {
        
        if (other.CompareTag("Obstacle"))
        {
             
            GameManager.Instance.GameOver();
        }
    }

    
    private void QuitGame()
    {
      
        #if UNITY_STANDALONE
        Application.Quit();
        #endif

       
        #if UNITY_EDITOR
        UnityEditor.EditorApplication.isPlaying = false;
        #endif
    }
}
