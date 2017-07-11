var startBtn, results, key, languageOptions;
var SDK;
var recognizer;
var previousSubscriptionKey;
var buttonOn;

document.addEventListener("DOMContentLoaded", function() {
    createBtn = document.getElementById("createBtn");
    startBtn = document.getElementById("record");
    results = document.getElementById("results");
    
    //DON'T HARDCODE KEY! Will need to change.
    key = "8d7c6a2f60c7473ba8dc90cf3a7ea068";
    
    languageOptions = document.getElementById("languageOptions");

    languageOptions.addEventListener("change", function() {
        Setup();
    });

    startBtn.addEventListener("click", function() {
        if (!recognizer || previousSubscriptionKey != key) {
            previousSubscriptionKey = key;
            Setup();
        }
        var transcript = document.getElementById("results").innerHTML;
        if (buttonOn) {
            RecognizerStart(SDK, recognizer, transcript);
            document.getElementById("status").innerHTML = 'Press q or button to stop recording';

            buttonOn = false;
        } else {
            RecognizerStop(SDK, recognizer);
            buttonOn = true;
            document.getElementById("status").innerHTML = 'Press Button to begin recording';
        }
    });


    Initialize(function(speechSdk) {
        SDK = speechSdk;
        //enable start button
        buttonOn = true;
    });
    
    document.onkeypress = function(e) {
        if(e.key === "q" || e.key === "Q"){
            RecognizerStop(SDK, recognizer);
            buttonOn = true;
            dontAnimate();
            document.getElementById("status").innerHTML = 'Press Button to begin recording';
        }
    }
});

function Setup() {
    recognizer = RecognizerSetup(SDK, SDK.RecognitionMode.Conversation, languageOptions.value, SDK.SpeechResultFormat.Simple, key);
}

function UpdateTranscribedText(transcript) {
    results.innerHTML = transcript + "&#8739;";
}