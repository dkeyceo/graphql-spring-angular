import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Apollo } from 'apollo-angular';
import { CREATE_BRAND } from 'src/app/graphql/mutations.graphql';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  name: ''

  constructor(private apollo: Apollo,
    private router: Router) { }

  ngOnInit(): void {
  }

  onCreate(){
    this.apollo.mutate({
      mutation: CREATE_BRAND,
      variables: {
        brandDto: {
          name: this.name
        }
      }
    }).subscribe(() => {
      this.router.navigate(['/'])
    }, err => alert(err))
  }
}
