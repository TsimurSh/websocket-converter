const StompJS = require('@stomp/stompjs');
const axios = require('axios');

Object.assign(global, { WebSocket: require('ws') });

const client = new StompJS.Client({
  brokerURL: `wss://dslocate.datasystem.pl/fleetservice`,
  connectHeaders: {
    login: ``,
    passcode: `827ccb0eea8a706c4c34a16891f84e7b`,
  },
  debug: function () {},
  reconnectDelay: 5000,
  heartbeatIncoming: 30000,
  heartbeatOutgoing: 30000,
  stompVersions: new StompJS.Versions(['1.1']),
});
client.onConnect = (frame) => {
  if (frame.command === 'CONNECTED') {
    client.subscribe('/vehicles/positions', (res) => {
      if (res.command === 'MESSAGE') {
        switch (res.headers['message']) {
          case 'current state': {
            console.log(res.body);
            break;
          }
          case 'vehicle update':
            console.log(res.body);
            axios.post('http://localhost:8889/api/update-position', JSON.parse(res.body))
                .then(() => {
                })
                .catch((error) => {
                  console.error('Error : ', error)
                })
            break;
        }
      }
    });
  } else if (frame.headers['message'] === '\n') {
    client.publish({ destination: '', body: '\n' });
  }
};
client.onStompError = function (frame) {
  console.log('Broker reported error: ' + frame.headers['message']);
  console.log('Additional details: ' + frame.body);
};
client.activate();
