import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-recommend-product',
  templateUrl: './recommend-product.component.html',
  styleUrls: ['./recommend-product.component.scss']
})
export class RecommendProductComponent implements OnInit {


  chart = {
    title: 'Test Chart',
    type: 'PieChart',
    data: [['Copper', 8.94], ['Silver', 10.49], ['Gold', 19.3], ['Platinum', 21.45]],
    columnNames: ['Element', 'Density'],
    options: {
      animation: {
        duration: 2500,
        easing: 'ease-in-out',
        startup: true,
        chartArea: {left: 0, top: 0, width: "100%", height: "100%"},
        height: 500,
        width: 500
      }
    }
  };

  chart1 = {
    title: 'Bar Chart',
      type: 'BarChart',
      columnNames: ['City', '2010 Population', '2000 Population'],
      roles: [{ role: 'annotation', type: 'string', index: 1 }, { role: 'annotation', type: 'string', index: 2 }],
      data: [
        ['New York City, NY', 8175000, '8.1M', 8008000, '8M'],
        ['Los Angeles, CA', 3792000, '3.8M', 3694000, '3.7M'],
        ['Chicago, IL', 2695000, '2.7M', 2896000, '2.9M'],
        ['Houston, TX', 2099000, '2.1M', 1953000, '2.0M'],
        ['Philadelphia, PA', 1526000, '1.5M', 1517000, '1.5M']
      ],
      options: {
        annotations: {
          alwaysOutside: true,
          textStyle: {
            fontSize: 12,
            auraColor: 'none',
            color: '#555'
          },
          boxStyle: {
            stroke: '#ccc',
            strokeWidth: 1,
            gradient: {
              color1: '#f3e5f5',
              color2: '#f3e5f5',
              x1: '0%',
              y1: '0%',
              x2: '100%',
              y2: '100%'
            }
          }
        },
        hAxis: {
          title: 'Total Population',
          minValue: 0
        },
        vAxis: {
          title: 'City'
        }
      }
  };

  chart2 = {
    // title: 'Styled Line Chart',
    //   type: 'LineChart',
    //   columnNames: ['Element', 'Density'],
    //   roles: [
    //     { type: 'number', role: 'interval' },
    //     { type: 'number', role: 'interval' },
    //     { type: 'string', role: 'annotation' },
    //     { type: 'string', role: 'annotationText' },
    //     { type: 'boolean', role: 'certainty' }
    //   ],
    //   data: [
    //     ['April', 1000, 900, 1100, 'A', 'Stolen data', true],
    //     ['May', 1170, 1000, 1200, 'B', 'Coffee spill', true],
    //     ['June', 660, 550, 800, 'C', 'Wumpus attack', true],
    //     ['July', 1030, null, null, null, null, false]
    //   ]
    title: 'Test Chart',
    type: 'PieChart',
    data: [['Copper', 8.94], ['Silver', 10.49], ['Gold', 19.3], ['Platinum', 21.45]],
    columnNames: ['Element', 'Density'],
    options: {
      animation: {
        duration: 2500,
        easing: 'ease-in-out',
        startup: true
      }
    }
  };
  constructor() { }

  ngOnInit() {
  }

}
