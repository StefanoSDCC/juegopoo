using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class GameManager : MonoBehaviour
{
    
    public static GameManager Instance { get; private set; }

    [Space(10)]
 
    public Animator animator;

    [Space(10)]

    public TextMeshProUGUI gameStartText;
    public TextMeshProUGUI gameOverText;
    public Button retryButton;

    [Space(10)]
    
    public float score;

    [Space(10)]
   
    public TextMeshProUGUI scoreText;
    public TextMeshProUGUI hiscoreText;

   
    private PlayerController player;
    private ObstacleGenerator spawner;

    [Space(10)]
    
    public float initialGameSpeed = 5f;
    public float gameSpeedIncrease = 0.1f;

    
    public float gameSpeed { get; private set; }

    [Space(10)]
    
    public bool isGameStarted = false;
    public bool isGameOver = false;

  
    private void Awake()
    {
        
        if (Instance == null)
        {
            Instance = this;
        }
        else
        {
          
            DestroyImmediate(gameObject);
        }
    }

   
    private void OnDestroy()
    {
      
        if (Instance == this)
        {
            Instance = null;
        }
    }

    
    private void Start()
    {
     
        Cursor.lockState = CursorLockMode.Locked;
        Cursor.visible = false;

      
        player = FindObjectOfType<PlayerController>();
        spawner = FindObjectOfType<ObstacleGenerator>();

        /
        animator.SetBool("isGameStarted", false);

    
        gameSpeedIncrease = 0;

         
        spawner.gameObject.SetActive(false);
    }

     
    public void NewGame()
    {
       
        gameSpeedIncrease = 0.1f;
        
        
        player.gameObject.SetActive(false);
 
        ObstacleController[] obstacles = FindObjectsOfType<ObstacleController>();
        foreach (var obstacle in obstacles)
        {
            Destroy(obstacle.gameObject);
        }

        
        gameSpeed = initialGameSpeed;

     
        score = 0f;

         
        enabled = true;

        
        player.gameObject.SetActive(true);
        spawner.gameObject.SetActive(true);
        
         
        gameOverText.gameObject.SetActive(false);
        retryButton.gameObject.SetActive(false);

        
        isGameOver = false;
        animator.SetBool("isGameStarted", true);
    }

   
    public void GameStart()
    {
         
        gameOverText.gameObject.SetActive(false);
        retryButton.gameObject.SetActive(false);

        
        UpdateHiscore();

         
        if (Input.GetKeyDown(KeyCode.Space) && !isGameStarted)
        {
            isGameStarted = true;
            
            NewGame();
           
            gameStartText.gameObject.SetActive(false);
            animator.SetBool("isGameStarted", true);
        }
    }

    
    private void Update()
    {
         
        if (!isGameOver)
        {
            
            GameStart();

             
            gameSpeed += gameSpeedIncrease * Time.deltaTime;

             
            score += gameSpeed * Time.deltaTime;
            scoreText.text = Mathf.FloorToInt(score).ToString("D5");
        }
    }


    private void UpdateHiscore()
    {
         
        float hiscore = PlayerPrefs.GetFloat("hiscore", 0);

        
        hiscoreText.text = Mathf.FloorToInt(hiscore).ToString("D5");

         
        if (isGameOver)
        {
             
            if (score > hiscore)
            {
                hiscore = score;
                PlayerPrefs.SetFloat("hiscore", hiscore);
            }

             
            hiscoreText.text = Mathf.FloorToInt(hiscore).ToString("D5");
        }
    }

    
    public void GameOver()
    {
        
        gameSpeed = 0f;
        enabled = false;

        
        animator.SetBool("isGameOver", true);

       
        spawner.gameObject.SetActive(false);

        
        gameOverText.gameObject.SetActive(true);
        retryButton.gameObject.SetActive(true);

       
        isGameOver = true;

         
        UpdateHiscore();
    }
}
