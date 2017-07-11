// Replace certain words with their punctuation counterparts.
// If checkbox is checked, replace all instances of corresponding word with punctuation (accounting for space, end punctuation, etc)

function replacePunctuation(transcript) {
    if (document.getElementById("replaceCommas").checked) {
        transcript = transcript.replace(/ comma | comma. |comma./gi, ", ");
    }
    if (document.getElementById("replaceDashes").checked) {
        transcript = transcript.replace(/ dash | dash. |dash./gi, "-");
    }
    if (document.getElementById("replaceExPoints").checked) {
        //replace exclamation points in the middle of a sentence and at the end
        transcript = transcript.replace(/ exclamation point | exclamation point. |exclamation point./gi, "! ");
    }
    if (document.getElementById("replaceQuestionMarks").checked) {
        //replace question marks in the middle of a sentence and at the end
        transcript = transcript.replace(/ question mark.| question mark? | question mark |question mark./gi, "? ");
    }
    //NOTE: PERIODS CAN BE DECTECTED WHEN A USER PAUSES.
//    if (document.getElementById("replacePeriods").checked) {
//        transcript = transcript.replace(/ period. | period |period./gi, ". ");
//    }
    if (document.getElementById("replaceColons").checked) {
        transcript = transcript.replace(/ colon | colon. |colon./gi, ": ");
    } 
    if (document.getElementById("replaceQuotes").checked) {
        transcript = transcript.replace(/ opening quotation mark | opening quotation mark. | opening quotation mark.|opening quotation mark./gi, ' "');
        transcript = transcript.replace(/ closing quotation mark | closing quotation mark. | closing quotation mark.|closing quotation mark./gi, '" ');
    }
    if (document.getElementById("replaceParentheses").checked) {
        transcript = transcript.replace(/ opening parenthesis | opening parenthesis. | opening parenthesis.|opening parenthesis.| opening parentheses | opening parentheses. | opening parentheses.|opening parentheses./gi, "(");
        transcript = transcript.replace(/closing parenthesis |closing parentheses |closing parenthese.| closing parethese |closing parenthese /gi, ")");
    }
    return transcript;
}

// Capitalizes first letter after periods, exclamation points, and question marks.
function capitalizeAfterPunctuation(transcript) {
    var startOfSentence = true;
    for (i = 0; i < transcript.length - 1; i++) {
        var currentChar = transcript.substring(i, i + 1);
        if (currentChar === "." || currentChar === "!" || currentChar === "?") {
            startOfSentence = true;
        }

        if (isLetter(currentChar) && startOfSentence) {
            transcript = transcript.substring(0, i) + currentChar.toUpperCase() + transcript.substring(i + 1, transcript.length);
            startOfSentence = false;
        }
    }
    return transcript;
}

function isLetter(str) {
    return str.match(/[a-z]/i);
}