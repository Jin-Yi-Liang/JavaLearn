const actions={
    idle:{
        name:"idle",
        folder:"./frames/jump_frames_rgba",
        startFrame:90,
        endFrame:120,
        fps:12
    },

    walk:{
        name:"walk",
        folder:"./frames/walk_frames_rgba",
        startFrame:44,
        endFrame:77,
        fps:12
    },

    jump:{
        name:"jump",
        folder:"./frames/jump_frames_rgba",
        startFrame:1,
        endFrame:30,
        fps:12
    },

    slide:{
        name:"slide",
        folder:"./frames/slide_frames_rgba",
        startFrame:30,
        endFrame:120,
        fps:12
    },

    dodge:{
        name:"dodge",
        folder:"./frames/dodge_frames_rgba",
        startFrame:1,
        endFrame:120,
        fps:12
    }
};

const fileConfig = {
    prefix:"frame_",
    extension:".png",
    digits:4
};

const display=document.getElementById("display");
const actionSelect=document.getElementById("actionSelect");
const playButton=document.getElementById("playButton");
const prevButton=document.getElementById("prevButton");
const nextButton=document.getElementById("nextButton");
const actionName=document.getElementById("actionName");
const frameNumber=document.getElementById("frameNumber");
const totalFrames=document.getElementById("totalFrames");
const fpsValue=document.getElementById("fps");

let currentActionName="idle";
let currentAction=actions[currentActionName];
let currentFrame=currentAction.startFrame;
let playing=true;
let lastFrameTime=0;

const imgCache=new Map();

function getFramePath(frame){
    const number=String(frame).padStart(fileConfig.digits,"0");
    return (currentAction.folder
        +"/"
        +fileConfig.prefix
        +number
        +fileConfig.extension
    );
}

function preloadFrame(){
    for(
        let frame=currentAction.startFrame;
        frame<=currentAction.endFrame;
        frame++
    ){
        const path=getFramePath(frame);
        if(imgCache.has(path)){
            continue;
        }
        const img=new Image();
        img.src=path;
        imgCache.set(path,img);
    }
}

function showFrame(frame){
    currentFrame=frame;
    display.src=getFramePath(frame);
    frameNumber.textContent=frame;
}

function prevFrame(){
    currentFrame--;
    if(currentFrame<currentAction.startFrame){
        currentFrame=currentAction.endFrame;
    }
    showFrame(currentFrame);
}

function nextFrame(){
    currentFrame++;
    if(currentFrame>currentAction.endFrame){
        currentFrame=currentAction.startFrame;
    }
    showFrame(currentFrame);
}

function switchAction(name){
    if(!actions[name]){
        console.error("action does not exists",name);
        return;
    }
    currentActionName=name;
    currentAction=actions[name];
    currentFrame=currentAction.startFrame;
    lastFrameTime=0;
    actionName.textContent=name;
    fpsValue.textContent=currentAction.fps;
    totalFrames.textContent=currentAction.endFrame-currentAction.startFrame+1;

    preloadFrame();
    showFrame(currentAction.startFrame);
    playing=true;
    playButton.textContent="stop";
}

function animationLoop(timeStamp){
    const frameDuration=1000/currentAction.fps;
    if(playing){
        if(timeStamp-lastFrameTime>=frameDuration){
            nextFrame();
        }
        lastFrameTime=timeStamp-(timeStamp-lastFrameTime)%frameDuration;
    }
    requestAnimationFrame(animationLoop);
}


//按钮事件监听
actionSelect.addEventListener("change",function(){
        switchAction(actionSelect.value);
    }
)

playButton.addEventListener("click",function(){
        playing=!playing;
        if(playing){
            playButton.textContent="stop";
            lastFrameTime=performance.now();
        }
        else{
            playButton.textContent="play";
        }
    }
)

prevButton.addEventListener("click",function(){
        playing=false;
        playButton.textContent="play";
        prevFrame();
    }
)

nextButton.addEventListener("click",function(){
        playing=false;
        playButton.textContent="play";
        nextFrame();
    }
)

//初始化
switchAction("idle");
requestAnimationFrame(animationLoop);