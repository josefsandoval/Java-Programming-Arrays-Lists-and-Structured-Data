// On doument load resolve the SDK dependecy
function Initialize(onComplete) {
    require(["Speech.Browser.Sdk"], function(SDK) {
        onComplete(SDK);
    });
}

// Setup recongizer
function RecognizerSetup(SDK, recognitionMode, language, format, subscriptionKey) {
    var recognizerConfig = new SDK.RecognizerConfig(
        new SDK.SpeechConfig(
            new SDK.Context(
                new SDK.OS(navigator.userAgent, "Browser", null),
                new SDK.Device("SpeechSample", "SpeechSample", "1.0.00000"))),
        recognitionMode,
        language,
        format);

    var authentication = new SDK.CognitiveSubscriptionKeyAuthentication(subscriptionKey);

    return SDK.CreateRecognizer(recognizerConfig, authentication);
}

// Start recognition
function RecognizerStart(SDK, recognizer, transcript) {
    recognizer.Recognize((event) => {
        if (event.name === "SpeechSimplePhraseEvent" && event.Result.DisplayText != undefined) {
            var nextPhrase = event.Result.DisplayText;
            switch (nextPhrase) {

                /*dictation and interactive mode change how speech is received
                    *'new line' is read as 'newline'
                    *use fall-through
                */
                case "New line.":
                case "Newline.":
                    transcript += '<br>';
                    break;
                    
                case "New paragraph.":
                case "Newparagraphine.":
                    transcript += '<br><br>';
                    break;

                case "Delete previous sentence.":
                    var end = Math.max(transcript.lastIndexOf(".", transcript.length - 3),
                        Math.max(transcript.lastIndexOf("?", transcript.length - 3),
                            transcript.lastIndexOf("!", transcript.length - 3)));
                    //find last index of period, question mark, or exclamation point
                    if (end == -1) {
                        transcript = "";
                    } else {
                        transcript = transcript.substring(0, end + 2);
                    }
                    break;

                case "Delete previous word.":
                    var end = transcript.lastIndexOf(" ", transcript.length - 2);
                    //find last index of space, excluding space that immediately follows previous word
                    if (end == -1) {
                        transcript = "";
                    } else {
                        transcript = transcript.substring(0, end + 1); //crop out previous word from transcript
                    }
                    break;

                default:
                    nextPhrase = replacePunctuation(nextPhrase);
                    nextPhrase = capitalizeAfterPunctuation(nextPhrase);
                    transcript += nextPhrase + " ";
            }

            UpdateTranscribedText(transcript);
        }

        })
        .On(() => {
            // The request succeeded. Nothing to do here.
        }, (error) => {
            console.error(error);
        });
}

// Stop recognition
function RecognizerStop(SDK, recognizer) {
    recognizer.AudioSource.TurnOff();

    // Clear cursor character
    var results = document.getElementById("results");
    results.innerHTML = results.innerHTML.substring(0, results.innerHTML.length - 1);
}