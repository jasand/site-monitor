/**
 * Created by jan.arne.sandnes on 12.10.15.
 */

//$(document).on('click', '.toggle-button', function() {
//    $(this).toggleClass('toggle-button-selected');
//});

var sensorHolder = {
    sensors : [
        {
            sensorIdent : "emulator-sensor-1",
            emulatorType : "sinusoidal",
            signalPeak : 750,
            signalMin : 250,
            signalPeriod : 60000,
            sampleArray : [],
            sampleIndex : 0,
            isActive : true
        }, {
            sensorIdent : "emulator-sensor-2",
            emulatorType : "sinusoidal",
            signalPeak : 750,
            signalMin : 250,
            signalPeriod : 60000,
            sampleArray : [],
            sampleIndex : 0,
            isActive : true
        }, {
            sensorIdent : "emulator-sensor-3",
            emulatorType : "triangle",
            signalPeak : 750,
            signalMin : 250,
            signalPeriod : 60000,
            sampleArray : [],
            sampleIndex : 0,
            isActive : true
        }, {
            sensorIdent : "emulator-sensor-4",
            emulatorType : "random",
            signalPeak : 750,
            signalMin : 250,
            signalPeriod : 60000,
            sampleArray : [],
            sampleIndex : 0,
            isActive : true
        }
    ]
};
var emulatorTemplate, newEmulatorTemplate;
var sampleFrequency = 5000;

// Function called when document loaded
$(document).ready(function () {

    // Compile template
    var source = $("#emulator-template").html();
    emulatorTemplate = Handlebars.compile(source);
    source = $("#new-emulator-template").html();
    newEmulatorTemplate = Handlebars.compile(source);

    // build initial signals
    for (var i = 0; i < sensorHolder.sensors.length; i++) {
        calculateSignal(i);
    }

    // Display initial emulators
    showTemplate(emulatorTemplate, sensorHolder);

    setMainScreenValues();

    // Change handler for emulator type
    $(".emulator-type-config").change(function() {
        console.log("Change type.");
        var splitRes = $(this).attr("id").split("-");
        var num = splitRes[splitRes.length - 1];
        var sensorType = $(this).val();
        sensorHolder.sensors[num].emulatorType = sensorType;
        calculateSignal(num);
    });

    // Change handler for signal period
    $(".emulator-period-config").change(function() {
        console.log("Change period.");
        var splitRes = $(this).attr("id").split("-");
        var num = splitRes[splitRes.length - 1];
        var periode = $(this).val();
        sensorHolder.sensors[num].signalPeriod = periode;
        calculateSignal(num);
    });

    // Change handler for peak value
    $(".sensor-peak-config").change(function() {
        console.log("Change upper.");
        var splitRes = $(this).attr("id").split("-");
        var num = splitRes[splitRes.length - 1];
        var peak = $(this).val();
        sensorHolder.sensors[num].signalPeak = peak;
        calculateSignal(num);
    });

    // Change handler for min value
    $(".sensor-min-config").change(function() {
        console.log("Change lower.");
        var splitRes = $(this).attr("id").split("-");
        var num = splitRes[splitRes.length - 1];
        var min = $(this).val();
        sensorHolder.sensors[num].signalMin = min;
        calculateSignal(num);
    });

    $('a.toggler').click(function(){
        $(this).toggleClass('off');
        var index = $(this).data("id");
        if($(this).is(".off")){
            sensorHolder.sensors[index].isActive = false;
        } else {
            sensorHolder.sensors[index].isActive = true;
        }
    });

    // Show new emulator modal
    $("#add-emulator").click(function() {
        console.log("Add emulato clicked");
        showTemplate(newEmulatorTemplate, sensorHolder);

        $("#emulatorModal").modal('show');

        $("#inputCancelBtn").click(function() {
            $("#emulatorModal").modal('hide');
        });

        $("#inputCreateBtn").click(function() {
            var sensor = {};
            sensor.sensorIdent = $("#inputSensorIdent").val();
            sensor.emulatorType = $("#inputEmulatorType").val();
            sensor.signalPeak = Number($("#inputPeakValue").val());
            sensor.signalMin = Number($("#inputMinValue").val());
            sensor.signalPeriod = Number($("#inputEmulatorPeriod").val());
            sensor.sampleArray = {};
            sensor.sampleIndex = 0;
            sensor.isActive = $("#inputActiveCheckbox").prop("checked");

            sensorHolder.sensors.push(sensor);
            calculateSignal(Number(sensorHolder.sensors.length - 1));

            $("#emulatorModal").modal('hide');
        });

        // Callback: On hidden, navigate back to originating view
        $('#emulatorModal').on('hidden.bs.modal', function (){
            console.log("Hiding...");
            showTemplate(emulatorTemplate, sensorHolder);
            setMainScreenValues();
        });
    });

    // Send readings every sampleFrequency milliseconds
    setInterval(function (){
        processEmulatorsAndPostEmulatedReadings();
    }, sampleFrequency);
});

function setMainScreenValues() {
    // Set values of emulators
    for (var i=0; i < sensorHolder.sensors.length; i++) {
        $("#select-emulator-type-" + i).val(sensorHolder.sensors[i].emulatorType);
        $("#select-emulator-period-" + i).val(sensorHolder.sensors[i].signalPeriod);
        $("#select-emulator-peak-" + i).val(sensorHolder.sensors[i].signalPeak);
        $("#select-emulator-min-" + i).val(sensorHolder.sensors[i].signalMin);
        if (sensorHolder.sensors[i].isActive) {
            $("#onOffToggler-" + i).toggleClass('on');
        } else {
            $("#onOffToggler-" + i).toggleClass('off');
        }
    }
}

// Show template
function showTemplate(template, data){
    var html = template(data);
    $('#content').html(html);
}

// Add emulator with template
function appendTemplate(template, data){
    var html = template(data);
    $('#content').append(html);
}

function processEmulatorsAndPostEmulatedReadings() {
    console.log("Processing emulators");
    for (var i=0; i<sensorHolder.sensors.length; i++) {
        var sensorIdent = sensorHolder.sensors[i].sensorIdent;
        var rawValue = sensorHolder.sensors[i].sampleArray[sensorHolder.sensors[i].sampleIndex];
        if (sensorHolder.sensors[i].isActive) {
            postEmulatedValue(sensorIdent, rawValue);
        }
        sensorHolder.sensors[i].sampleIndex++;
        if (sensorHolder.sensors[i].sampleIndex >= sensorHolder.sensors[i].sampleArray.length) {
            sensorHolder.sensors[i].sampleIndex = 0;
        }
    }
}

function calculateSignal(index) {
    if (sensorHolder.sensors[index].emulatorType == 'sinusoidal') {
        calculateSinusWave(index);
    } else if (sensorHolder.sensors[index].emulatorType == 'random') {
        calculateRandomSignal(index);
    } else {
        calculateTriangleWave(index);
    }
}

function calculateSinusWave(index) {
    // Kalkuler skala (over-nedre)/2
    var scale = Number((sensorHolder.sensors[index].signalPeak - sensorHolder.sensors[index].signalMin) / 2);
    // Regn ut hvor mange punkter i array
    var samplesLen = Math.floor(Number(sensorHolder.sensors[index].signalPeriod) / sampleFrequency);
    // Regn sinus og gange med skala
    sensorHolder.sensors[index].sampleArray = [];
    sensorHolder.sensors[index].sampleIndex = 0;
    for (var i=0; i<samplesLen; i++) {
        var sample = Math.floor(Math.sin((i/samplesLen) * 6.28) * scale + scale + Number(sensorHolder.sensors[index].signalMin));
        sensorHolder.sensors[index].sampleArray.push(sample);
    }
    console.log("Sinus: " + sensorHolder.sensors[index].sampleArray);
}

function calculateTriangleWave(index) {
    // Kalkuler range (over-nedre)
    var range = Number(sensorHolder.sensors[index].signalPeak - sensorHolder.sensors[index].signalMin);
    // Regn ut hvor mange punkter i array
    var samplesLen = Math.floor(Number(sensorHolder.sensors[index].signalPeriod) / sampleFrequency);
    var steps = (samplesLen / 2) +1;
    var step = range / steps;
    console.log("range=" + range + ", samplesLen=" + samplesLen + ", steps=" + steps + ", step=" + step);
    // Regn sinus og gange med skala
    sensorHolder.sensors[index].sampleArray = [];
    sensorHolder.sensors[index].sampleIndex = 0;
    for (var i=0; i<samplesLen; i++) {
        if (i < steps) {
            var sample = Math.floor((step * i) + Number(sensorHolder.sensors[index].signalMin));
            sensorHolder.sensors[index].sampleArray.push(sample);
        } else {
            var sample = Math.floor(range - (step * (i - steps + 2)) + Number(sensorHolder.sensors[index].signalMin));
            sensorHolder.sensors[index].sampleArray.push(sample);
        }
    }
    console.log("Triangle: " + sensorHolder.sensors[index].sampleArray);
}

function calculateRandomSignal(index) {
    var scale = Number(sensorHolder.sensors[index].signalPeak - sensorHolder.sensors[index].signalMin);
    var samplesLen = Math.floor(Number(sensorHolder.sensors[index].signalPeriod) / sampleFrequency);
    sensorHolder.sensors[index].sampleArray = [];
    sensorHolder.sensors[index].sampleIndex = 0;
    for (var i=0; i<samplesLen; i++) {
        var sample = Math.floor((Math.random()*scale) + Number(sensorHolder.sensors[index].signalMin));
        sensorHolder.sensors[index].sampleArray.push(sample);
    }
    console.log("Random: " + sensorHolder.sensors[index].sampleArray);
}

function postEmulatedValue(sensorIdent, value) {
    var postObj = {};
    postObj.sensorId = sensorIdent;
    postObj.sensorValue = value;
    var jsonString = JSON.stringify(postObj);
    console.log("JSON string: " + jsonString);
    $.ajax({
        type: "POST",
        url: "./basic",
        data: jsonString,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data){console.log("Data OK: " + data);},
        failure: function(errMsg) {
            console.log("Error posting: " + errMsg);
        }
    });
}

