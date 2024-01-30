"""
    This program will use Dijkstra's Algorithm to find the
    length of the shortest path from vertices v1 to vn.
"""
# Define imports
import math


def dijkstra(V, E):
    """
    Uses Dijkstra's Algorithm to find the length of the shortest
    path from vertices v1 to vn. Assume parameters are valid.

    Parameters:
        V - a list of vertices V = [v1, v2,...,vn]
        E - a list of tuples E = [(vi, vj, weight),...]
    Returns:
        path_len - the length of the shortest path from v1 to vn
    """
    # Set up variables for the algorithm
    tentative_distance = {}
    visited = {}

    # Tentative distances start as infinity
    # Each vertex starts as unvisited
    for i in range(len(V)):
        tentative_distance[V[i]] = math.inf
        visited[V[i]] = False

    # Start with the first vertex
    min_node = V[0]
    # Set the distance of the first vertex to 0
    tentative_distance[V[0]] = 0

    # The algorithm ends when the last vertex is visited
    while not visited[V[-1]]:
        # Update current node to the node with the lowest tentative distance
        current_node = min_node
        vi_neighbours_distance = []
        # Create list with tuples of adjacent vertices and edge weights
        # Account for different orderings and visited vertices
        for j in range(len(E)):
            if E[j][0] == current_node and not visited[E[j][1]]:
                vi_neighbours_distance.append((E[j][1], E[j][2]))
            elif E[j][1] == current_node and not visited[E[j][0]]:
                vi_neighbours_distance.append((E[j][0], E[j][2]))
        # Account for the case where all adjacent vertices have been visited
        if len(vi_neighbours_distance) != 0:
            min_node = vi_neighbours_distance[0][0]
            # Compare edge weights to tentative distances for each neighbour
            for k in range(len(vi_neighbours_distance)):
                temp = vi_neighbours_distance[k][1] + tentative_distance[current_node]
                if temp < tentative_distance[vi_neighbours_distance[k][0]]:
                    tentative_distance[vi_neighbours_distance[k][0]] = temp
                if temp < tentative_distance[min_node]:
                    min_node = vi_neighbours_distance[k][0]
        # Vertex is visited when each neighbour has been examined
        visited[current_node] = True

    # The shortest path length is the distance of the target vertex
    path_len = tentative_distance[V[-1]]
    return path_len


V = ["a", "b", "c", "d", "e", "z"]
E = [("a", "b", 4), ("a", "c", 2), ("b", "c", 1), ("b", "d", 5), ("c", "d", 8),
     ("c", "e", 10), ("d", "e", 2), ("d", "z", 6), ("e", "z", 3)]
test = dijkstra(V, E)
print(test)
