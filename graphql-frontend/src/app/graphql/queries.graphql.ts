import { gql } from "apollo-angular";

const GET_BRANDS = gql`
  query{
    findAllBrands{
      id
      name
    }
  }
`;

const GET_MODELS_BY_BRAND_ID = gql`
query FindModelsByBrand($brandId: Int){
  findModelsByBrandId(brandId: $brandId) {
      id
      name
      brand {
          name
      }
  }
}
`;

export {GET_BRANDS, GET_MODELS_BY_BRAND_ID};
