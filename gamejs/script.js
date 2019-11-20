var canvas = document.getElementById("topCanvas");
var ctx = canvas.getContext("2d");
var ballRadius = 8;
var paddleHeight = 10;
var paddleWidth = 135;
var paddleX = (canvas.width-paddleWidth)/2;
var rightPressed = false;
var leftPressed = false;
var speed = 2 ;

var x = [] ;
var y = [] ;
var dx = [] ;
var dy = [] ;
var n = prompt("Vvedite kolichestvo sharicov", 1) ;
var score = 0 ;

document.addEventListener("keydown", keyDownHandler, false);
document.addEventListener("keyup", keyUpHandler, false);


function keyDownHandler(e)
{
    if(e.keyCode == 39) 
    {
        rightPressed = true;
    }
    else 
    if(e.keyCode == 37) 
    {
        leftPressed = true;
    }
}

function keyUpHandler(e)
{
    if(e.keyCode == 39)
    {
        rightPressed = false;
    }
    else 
    if(e.keyCode == 37) 
    {
        leftPressed = false;
    }
}

function drawBall( xx, yy ) 
{
    ctx.beginPath();
    ctx.arc(xx, yy, ballRadius, 0, Math.PI*2);
    ctx.fillStyle = "green";
    ctx.fill();
    ctx.closePath();
}

function drawPaddle() 
{
    ctx.beginPath();
    ctx.rect(paddleX, canvas.height-paddleHeight, paddleWidth, paddleHeight);
    ctx.fillStyle = "red";
    ctx.fill();
    ctx.closePath();
}

function drawScore() 
{
  ctx.font = "24px Arial";
  ctx.fillStyle = "black";
  ctx.fillText("Score: "+ score, 8, 20);
}

function draw() 
{
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawPaddle();
    drawScore() ;

    for ( var i=0; i<n; i++ )
    {
        drawBall ( x[i], y[i] ) ;

        if(x[i] + dx[i] > canvas.width-ballRadius || x[i] + dx[i] < ballRadius) 
        {
            dx[i] = -dx[i];
        }
        if(y[i] + dy[i] < ballRadius) 
        {
            dy[i] = -dy[i];
        }
        else 
            if(y[i] + dy[i] > canvas.height-ballRadius) 
            {
                if(x[i] > paddleX && x[i] < paddleX + paddleWidth) 
                {
                    dx[i] = Math.random() * 2  + 1 ;
                    dy[i] = -Math.random() * 2  - 1 ;

                    if ( Math.random() < 0.5 )
                    {
                        dx[i] *= -1 ;
                    }

                    score ++ ;
                }
                else 
                {
                    alert("GAME OVER");
                    document.location.reload();
                }
            }

            x[i] += dx[i];
            y[i] += dy[i];
    }
    
    if(rightPressed && paddleX < canvas.width-paddleWidth) 
    {
        paddleX += 7;
    }
    else 
    if(leftPressed && paddleX > 0)
    {
        paddleX -= 7;
    }
}

for ( var i=0; i<n; i++ )
{
    x[i] = canvas.width/2;
    y[i] = canvas.height-30;
    dx[i] = Math.random() * 2  + 1 ;
    dy[i] = -Math.random() * 2  - 1 ;
}

if ( confirm("Hardcore mod?") )
    setInterval(draw, 3);
else
    setInterval(draw, 10);
