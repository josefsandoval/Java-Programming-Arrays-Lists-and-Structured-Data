function dontAnimate() {
    document.getElementById('record').classList.remove('Recording');
    document.getElementById('record').classList.add('notRecording');
}

document.getElementById('record').classList.add('notRecording');

function animateButton() {
    if (document.getElementById('record').classList.contains('notRecording')) {
        document.getElementById('record').classList.remove('notRecording');
        document.getElementById('record').classList.add('Recording');
    } else {
        dontAnimate();
    }
};