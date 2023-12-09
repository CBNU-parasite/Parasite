const pieChart = document.getElementById('tdgGraph');
const stickGraph = document.getElementById('detailed')

new Chart(pieChart,{
    type: 'pie',
    weight:1100,
    hoverBorderJoinStyle : 'round',
    data:{
        labels:['탄수화물','단백질','지방'],
        datasets:[{data: [70,10,20],
            backgroundColor: [
                'rgb(255, 99, 132)',
                'rgb(54, 162, 235)',
                'rgb(255, 205, 86)'
            ],
            hoverOffset: 4
    }]
    }
  }
)

new Chart(stickGraph, {
    type: 'bar',
    data: {
      labels: ['열량(kcal)', '당류', '나트륨', '콜레스테롤', '포화지방산', '트랜스지방'],
      datasets: [{
        label: '권장량(%)',
        data: [90, 40, 100, 80, 20, 30],
        borderWidth: 1,
        backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(255, 159, 64, 0.2)',
            'rgba(255, 205, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(201, 203, 207, 0.2)'
          ]
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      },
      hoverBackgroundColor:'rgba(255,255,255)'
    }
  });