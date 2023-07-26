import { gql } from "apollo-angular";

const GET_BRANDS = gql`
  query{
    findAllBrands{
      id
      name
    }
  }
`;

export {GET_BRANDS};
