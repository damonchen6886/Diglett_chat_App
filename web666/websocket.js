var ws;
var host = document.location.host;
var pathname = document.location.pathname;
var lastname = document.referrer;
var currentUser;

//register
function register() {
    var username = document.getElementById("registerUser").value;
    var password = document.getElementById("registerPassword").value;
    var passwordRepeat = document.getElementById("confirmPassword").value;
    var nickName = document.getElementById("registerNickName").value;
    var dob = document.getElementById("registerBoD").value;
    console.log(dob);
    console.log(typeof dob === 'string' );
    if (username.length === 0 || password.length === 0 || passwordRepeat.length === 0
        || nickName.length === 0 || dob.length === 0) {
        alert("All the fields are required for registration.");
    }
    else if (password !== passwordRepeat) {
        alert("The passwords you entered do not match.")
    }
    else {
        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", lastname + "rest/user/create", false);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send(JSON.stringify( {
                                       "username" : username,
                                       "password" : password,
                                       "nickname" : nickName,
                                       "dob" : dob,
                                       "status" : "offline"
                                   }));
        var msg = xhttp.responseText;
        console.log(msg)
        alert(msg);
        window.location.href = "index.html" ;
    }
}



//open websocket when login
function connect(username) {
    currentUser = username;
    console.log("ws://" + host + pathname  + "/" + username);
    ws = new WebSocket("ws://" + host + pathname  + "/" + username);
    console.log(ws.readyState == WebSocket.OPEN);
    ws.onmessage = function(event) {
        var log = document.getElementById("log");
        console.log(event.data);
        var message = JSON.parse(event.data);
        log.innerHTML += message.from + " : " + message.content + "\n";
    };
}

//load group list when login
function chatGroupList() {
    var group = getGroups();
    group.forEach(function(item) {
        var li = document.createElement("li");
        var text = document.createTextNode(item);
        li.appendChild(text);
        li.value = item;
        li.id=item;
        li.onclick = function(){chatIndividualList(item);chatWith(item);};
        document.getElementById("groupList").appendChild(li);
    });
}


//when clicked on group name
function chatIndividualList(item) {
    // var msg = friends();
    // var array = JSON.parse(msg);
    var array = getGroupMembers(item);

    document.getElementById("nameList").innerHTML="";

    //load chat history
    var msghistory = GroupHistory(item);
    document.getElementById("log").innerHTML="";
    document.getElementById("log").innerHTML= msghistory;

    //change button
    var button = document.createElement("button");
    var txt = document.createTextNode("Send");
    button.appendChild(txt);
    button.className = "button";
    button.type = "button";
    button.onclick = function(){groupSend(item)}
    document.getElementById("sendButton").innerHTML = "";
    document.getElementById("sendButton").appendChild(button);


    //load group members
    array.forEach(function(indiv) {
        var li = document.createElement("li");
        var text = document.createTextNode(indiv);
        li.appendChild(text);
        li.onclick = function(){chatIndividualHistory(indiv);chatWith(indiv);};
        document.getElementById("nameList").appendChild(li);
    });

}

//when click on peer name
function chatIndividualHistory(indiv) {


    var button = document.createElement("button");
    var txt = document.createTextNode("Send");
    button.appendChild(txt);
    button.className = "button";
    button.type = "button";
    button.onclick = function(){send(indiv)}
    document.getElementById("sendButton").innerHTML="";
    document.getElementById("sendButton").appendChild(button);

    var msghistory = individualHistory(indiv);

    document.getElementById("log").innerHTML = "";
    document.getElementById("log").innerHTML = msghistory;
}

//return individual History
function individualHistory(to) {

    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", lastname + "rest/user/history", false);
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send(JSON.stringify( {
                                   "owner" : currentUser,
                                   "peer" : to
                               }));



    var msgs = JSON.parse(xhttp.response);
    var res = "";
    msgs.forEach(function (msg) {
        res += msg + "\n";
    })
    return res;

}


//return group chat history
function GroupHistory(to) {

    var param = "username="+ currentUser + "&groupname="+ to;
    var url = lastname + "rest/group/getchathistory?" + param;
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", url, false);
    xhttp.setRequestHeader("Content-type", "application/json");

    xhttp.send();
    var msgs = JSON.parse(xhttp.response);
    var res = "";
    msgs.forEach(function (msg) {
        res += msg + "\n";
    })
    return res;
}

function chatWith(item) {
    var name = "Chat with ";
    name += item;
    document.getElementById("chatWith").innerHTML = name;
}

//helper function that return list of group name
function getGroups() {

    var param = "username="+ currentUser;
    var xhttp = new XMLHttpRequest();
    var url = lastname + "rest/group/getgroups?" + param;
    xhttp.open("POST", url, false);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    var msg = xhttp.responseText;
    var group = JSON.parse(msg);
    console.log(msg);
    return group;
}


//add or create group
function addgroup() {

    var groupname = document.getElementById("addgroup").value;
    var param = "username="+ currentUser + "&groupName=" + groupname;
    var xhttp = new XMLHttpRequest();
    var url = lastname + "rest/group/addgroup?" + param;
    xhttp.open("POST", url, false);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    var msg = xhttp.responseText;
    console.log(msg)
    alert(msg);
}


//return all the user names of a group
function getGroupMembers(groupname) {
    var param = "groupname="+ groupname;
    var xhttp = new XMLHttpRequest();
    var url = lastname + "rest/group/getgroupusers?" + param;
    xhttp.open("POST", url, false);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
    var msg = xhttp.responseText;
    var members = JSON.parse(msg);
    console.log(msg);
    return members;
}

//send ind msg
function send(to) {
    console.log("individual send");
    var content = document.getElementById("msg").value;
    var json = JSON.stringify({
                                  "content":content,
                                  "to" :to,
                                  "type" :"INDIVIDUAL"
                              });
    ws.send(json);
}










function save(cp_value){
    var num = new Object;
    num.cp_keynum ="key_num";
    num.cp_num_value = cp_value;
    var str = JSON.stringify(num); // 将对象转换为字符串
    localStorage.setItem(num.cp_keynum,str);

}

function find(){
    var cp_keynum = "key_num";
    var str = localStorage.getItem(cp_keynum);
    var find_result = document.getElementById("find_result");
    var num = JSON.parse(str);
    return num.cp_num_value;
}



function groupSend(to) {
    console.log("group send")
    var content = document.getElementById("msg").value;
    var json = JSON.stringify({
                                  "content":content,
                                  "to" :to,
                                  "type" :"GROUP"
                              });
    ws.send(json);
}


function regSucPop() {
    alert("User successfully registered!");
    window.location.href = "index.html";
}

function signup(){
    window.location.href = "signup.html";
}

var attempt = 3; // Variable to count number of attempts.
// Below function Executes on click of login button.


//validate user
function validate(){
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var xhttp = new XMLHttpRequest();
    xhttp.open("POST",  lastname + "rest/user/validate", false);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify( {
                                   "username" : username,
                                   "password" : password
                               }));


    var msg = xhttp.responseText;
    console.log(msg);
    if ( msg == "correct"){
        save(username);
        window.location.href = "app.html" ;

        // Redirecting to other page.
        return false;
    }
    else if (msg === "no user") {
        alert("User not exists.")
    }
    else{
        attempt --;// Decrementing by one.
        alert("You have" + attempt + "attempt left");
// Disabling fields after 3 attempts.
        if( attempt == 0){
            document.getElementById("username").disabled = true;
            document.getElementById("password").disabled = true;
            document.getElementById("submit").disabled = true;
            return false;
        }
    }
}


function send2() {
    console.log("ws://" + host + pathname  + "/" + username);
    ws = new WebSocket("ws://" + host + pathname  + "/" + username);
    ws.onmessage = function(event) {
        var log = document.getElementById("log");
        console.log(event.data);
        var message = JSON.parse(event.data);
        log.innerHTML += message.from + " : " + message.content + "\n";
    };
}