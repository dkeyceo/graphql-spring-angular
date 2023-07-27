import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ActivationEnd, Router } from '@angular/router';
import { Apollo } from 'apollo-angular';
import { UPDATE_BRAND } from 'src/app/graphql/mutations.graphql';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  name = ''

  constructor(private apollo: Apollo,
    private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.name = this.activatedRoute.snapshot.params['name'];

  }

  onUpdate(){
    const id = this.activatedRoute.snapshot.params['id'];

    this.apollo.mutate({
      mutation: UPDATE_BRAND,
      variables: {
        id: id,
        brandDto: {
          name: this.name
        }
      }
    }).subscribe(() => {
      this.router.navigate(['/']);
    }, err => alert(err));
  }
}
